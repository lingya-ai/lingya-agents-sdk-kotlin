package cloud.lingya.agents.sdk

import cloud.lingya.agents.sdk.generated.model.AiChatInput
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.withTimeout
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.concurrent.atomic.AtomicInteger

class TokenClientTest {
    private val submission = """{"conversationId":"conversation","messageId":"message","disposition":"queued","status":"pending"}"""

    @Test
    fun `503 retry-after cannot replay a consumed token`() {
        MockWebServer().use { server ->
            server.enqueue(MockResponse().setResponseCode(503).addHeader("Retry-After", "0"))
            server.enqueue(MockResponse().setResponseCode(201).setBody(submission))
            val count = AtomicInteger()
            val client = AgentsClient(AgentsClientOptions(server.url("/").toString(), "channel", AgentsAuth.Token {
                "fresh-${count.incrementAndGet()}"
            })).blocking()
            assertThrows(Exception::class.java) { client.chat.createChat(AiChatInput("hello")) }
            assertEquals(1, count.get())
            assertEquals(1, server.requestCount)
            client.chat.createChat(AiChatInput("hello"))
            assertEquals(2, count.get())
            assertEquals(2, server.requestCount)
        }
    }

    @Test
    fun `canceling request cancels suspended issuer without sending`() = runBlocking {
        MockWebServer().use { server ->
            val entered = CompletableDeferred<Unit>()
            val exited = CompletableDeferred<Unit>()
            val client = AgentsClient(AgentsClientOptions(server.url("/").toString(), "channel", AgentsAuth.Token {
                entered.complete(Unit)
                try { awaitCancellation() } finally { exited.complete(Unit) }
            }))
            withTimeout(5000) {
                val request = async { client.chat.createChat(AiChatInput("hello")) }
                entered.await()
                request.cancelAndJoin()
                exited.await()
            }
            assertEquals(0, server.requestCount)
        }
    }

    @Test
    fun `concurrent requests issue independently and presign is rejected`() = runBlocking {
        MockWebServer().use { server ->
            repeat(2) { server.enqueue(MockResponse().setResponseCode(201).setBody(submission)) }
            val count = AtomicInteger()
            val client = AgentsClient(AgentsClientOptions(server.url("/").toString(), "channel", AgentsAuth.Token {
                assertEquals("channel", it.channelId)
                val id = count.incrementAndGet()
                delay(10)
                "fresh-$id"
            }))
            coroutineScope {
                val first = async { client.chat.createChat(AiChatInput("hello")) }
                val second = async { client.chat.createChat(AiChatInput("hello")) }
                first.await()
                second.await()
            }
            val requests = listOf(server.takeRequest(), server.takeRequest())
            assertEquals(setOf("fresh-1", "fresh-2"), requests.map { it.getHeader("X-OpenAPI-Token") }.toSet())
            for (request in requests) {
                assertEquals(setOf("X-OpenAPI-Token"), request.headers.names().filter { it.startsWith("X-OpenAPI-") }.toSet())
            }
            assertThrows(Exception::class.java) { client.blocking().configuration.createOpenApiToken() }
            assertThrows(IllegalStateException::class.java) { client.forUser("other") }
            assertEquals(2, count.get())
            assertEquals(2, server.requestCount)
        }
    }

    @Test
    fun `401 and redirect are not retried and manual retry acquires new token`() {
        MockWebServer().use { server ->
            server.enqueue(MockResponse().setResponseCode(401))
            server.enqueue(MockResponse().setResponseCode(302).addHeader("Location", server.url("/redirect")))
            server.enqueue(MockResponse().setResponseCode(201).setBody(submission))
            val count = AtomicInteger()
            val client = AgentsClient(AgentsClientOptions(server.url("/").toString(), "channel", AgentsAuth.Token {
                "fresh-${count.incrementAndGet()}"
            })).blocking()
            repeat(2) { assertThrows(ApiException::class.java) { client.chat.createChat(AiChatInput("hello")) } }
            client.chat.createChat(AiChatInput("hello"))
            assertEquals(3, count.get())
            assertEquals(3, server.requestCount)
        }
    }

    @Test
    fun `invalid token and callback failure do not send or leak token`() {
        MockWebServer().use { server ->
            for (token in listOf("", "secret\r\nheader", "invalid token", "中文")) {
                val client = AgentsClient(AgentsClientOptions(server.url("/").toString(), "channel", AgentsAuth.Token { token })).blocking()
                val error = assertThrows(Exception::class.java) { client.chat.createChat(AiChatInput("hello")) }
                assertFalse(error.toString().contains("secret"))
            }
            val client = AgentsClient(AgentsClientOptions(server.url("/").toString(), "channel", AgentsAuth.Token { error("secret-token") })).blocking()
            val error = assertThrows(Exception::class.java) { client.chat.createChat(AiChatInput("hello")) }
            assertFalse(error.toString().contains("secret-token"))
            assertEquals(0, server.requestCount)
        }
    }

    @Test
    fun `SSE collection acquires once and new collection gets fresh token`() = runBlocking {
        MockWebServer().use { server ->
            repeat(2) { server.enqueue(MockResponse().setHeader("Content-Type", "text/event-stream")
                .setBody("data: {\"probeId\":\"probe\",\"sequence\":1,\"serverElapsedMs\":0}\n\ndata: {\"probeId\":\"probe\",\"sequence\":2,\"serverElapsedMs\":1}\n\n")) }
            val count = AtomicInteger()
            val client = AgentsClient(AgentsClientOptions(server.url("/").toString(), "channel", AgentsAuth.Token { "fresh-${count.incrementAndGet()}" }))
            val flow = client.chat.probeEventStream(cloud.lingya.agents.sdk.generated.model.ChatStreamProbeInput("probe"))
            assertEquals(0, count.get())
            repeat(2) { assertEquals(2, flow.toList().size) }
            assertEquals(2, count.get())
        }
    }
}

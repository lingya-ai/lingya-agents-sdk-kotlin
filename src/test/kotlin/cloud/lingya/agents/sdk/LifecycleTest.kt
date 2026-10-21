package cloud.lingya.agents.sdk

import cloud.lingya.agents.sdk.generated.model.AiChatStreamInput
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.concurrent.atomic.AtomicInteger

class LifecycleTest {
    private data class Vectors(val httpErrors: List<ErrorVector> = emptyList())
    private data class ErrorVector(val status: Int, val body: ErrorBody, val requestId: String)
    private data class ErrorBody(val code: String, val message: String, val fields: List<ApiException.FieldError>)

    @Test
    fun `shared HTTP vectors expose business fields and request identifiers`() {
        val mapper = com.fasterxml.jackson.module.kotlin.jacksonObjectMapper().configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        val vectors = mapper.readValue(java.io.File("test-vectors/lifecycle-v1.json"), Vectors::class.java)
        for (vector in vectors.httpErrors) {
            val error = ApiException(vector.status, mapper.writeValueAsString(vector.body), requestId = vector.requestId)
            assertEquals("http", error.kind)
            assertEquals(vector.body.code, error.code)
            assertEquals("fixture failure", error.message)
            assertEquals("title", error.fields.first().field)
            assertEquals(vector.requestId, error.requestId)
        }
    }

    @Test
    fun `metadata export keeps binary bytes and prefers UTF8 filename`() = runBlocking {
        MockWebServer().use { server ->
            server.enqueue(MockResponse().setBody(okio.Buffer().write(byteArrayOf(0, -1))).addHeader("Content-Type", "text/csv").addHeader("Content-Disposition", "attachment; filename=report.csv; filename*=UTF-8''%E4%B8%AD.csv"))
            val client = AgentsClient(AgentsClientOptions(server.url("/").toString(), "c", AgentsAuth.Token { "fresh" }))
            val file = client.sql.exportSqlQueryResultWithMetadata("c", "r", cloud.lingya.agents.sdk.generated.api.SQLApi.FormatExportSqlQueryResult.CSV)
            assertArrayEquals(byteArrayOf(0, -1), file.bytes)
            assertEquals("中.csv", file.filename)
            assertEquals("text/csv", file.contentType)
            assertTrue(server.takeRequest().path!!.endsWith("?format=CSV"))
        }
    }

    @Test
    fun `total timeout includes token acquisition and sends nothing`() = runBlocking {
        MockWebServer().use { server ->
            val client = AgentsClient(AgentsClientOptions(server.url("/").toString(), "c", AgentsAuth.Token { awaitCancellation() }))
            val error = try {
                client.configuration.getAgentsConfig(RequestOptions(timeoutMs = 30))
                fail<ApiException>("Expected timeout")
            } catch (error: ApiException) { error }
            assertEquals("timeout", error.kind)
            assertEquals(0, server.requestCount)
        }
    }

    @Test
    fun `late token cannot open websocket after timeout even when issuer ignores cancellation`() = runBlocking {
        MockWebServer().use { server ->
            val release = kotlinx.coroutines.CompletableDeferred<Unit>()
            val client = AgentsClient(AgentsClientOptions(server.url("/").toString(), "c", AgentsAuth.Token {
                kotlinx.coroutines.withContext(kotlinx.coroutines.NonCancellable) { release.await() }
                "late"
            }))
            withTimeout(1000) {
                val sub = client.chat.subscribeChatEvents("c", AiChatStreamInput("m"), "websocket", RequestOptions(timeoutMs = 20))
                try {
                    sub.events.toList()
                    fail<Unit>("Expected timeout")
                } catch (error: ApiException) { assertEquals("timeout", error.kind) }
            }
            release.complete(Unit)
            kotlinx.coroutines.delay(30)
            assertEquals(0, server.requestCount)
        }
    }

    @Test
    fun `websocket control frames complete without synthesizing End and issue new tokens`() = runBlocking {
        MockWebServer().use { server ->
            repeat(2) {
                server.enqueue(MockResponse().withWebSocketUpgrade(object : WebSocketListener() {
                    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) { webSocket.close(code, reason) }
                    override fun onOpen(webSocket: WebSocket, response: Response) {
                        webSocket.send("""{"id":"m","event":"ready"}""")
                        webSocket.send("""{"id":"m","event":"future","data":{"type":"future","kept":true}}""")
                        webSocket.send("""{"id":"m","event":"complete"}""")
                    }
                }))
            }
            val issued = AtomicInteger()
            val client = AgentsClient(AgentsClientOptions(server.url("/").toString(), "c", AgentsAuth.Token { "fresh-${issued.incrementAndGet()}" }))
            withTimeout(5000) {
                repeat(2) {
                    val subscription = client.chat.subscribeChatEvents("c", AiChatStreamInput("m"), "websocket", RequestOptions(timeoutMs = 2000))
                    assertEquals(listOf("future"), subscription.events.toList().map { it.type })
                    subscription.closed.await()
                }
            }
            assertEquals(2, issued.get())
            repeat(2) { assertEquals("xlt-openapi-token.fresh-${it + 1}", server.takeRequest().getHeader("Sec-WebSocket-Protocol")) }
        }
    }
}

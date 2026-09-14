package ai.lingya.agents.sdk

import ai.lingya.agents.sdk.generated.model.AiChatInput
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.OkHttpClient
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.net.SocketTimeoutException
import java.time.Clock
import java.time.Instant
import java.time.ZoneOffset
import java.time.Duration
import java.util.Base64

class LingyaAgentsClientTest {
    private val server = MockWebServer()

    @AfterEach
    fun closeServer() {
        server.close()
    }

    @Test
    fun `signs final encoded request and decodes a successful response`() = runTest {
        server.enqueue(
            MockResponse()
                .setResponseCode(201)
                .addHeader("Content-Type", "application/json")
                .setBody("""{"conversationId":"conversation-1","messageId":"message-1","disposition":"queued","status":"pending"}"""),
        )
        val client = newClient().forUser("外部用户-1")

        val result = client.createChat(AiChatInput("你好"))

        assertEquals("message-1", result.messageId)
        val request = server.takeRequest()
        assertEquals("/api/agents/channel/openapi/v1/channel/chat", request.requestUrl!!.encodedPath)
        assertEquals(ACCESS_KEY, request.headers["X-OpenAPI-AK"])
        assertEquals("1735689600", request.headers["X-OpenAPI-Timestamp"])
        assertEquals(NONCE, request.headers["X-OpenAPI-Nonce"])
        assertEquals("5aSW6YOo55So5oi3LTE", request.headers["X-OpenAPI-User"])
        assertTrue(request.headers["X-OpenAPI-Signature"]!!.matches(Regex("[0-9a-f]{64}")))
        assertTrue(request.body.readUtf8().contains("你好"))
        assertFalse(newClient().toString().contains(SECRET))
    }

    @Test
    fun `maps HTTP errors without exposing the secret`() {
        server.enqueue(MockResponse().setResponseCode(401).setBody("""{"code":"UNAUTHORIZED"}"""))
        val exception = assertThrows(LingyaApiException::class.java) {
            newClient().blockingForUser("user-1").createChat(AiChatInput("hello"))
        }
        assertEquals(401, exception.statusCode)
        assertFalse(exception.toString().contains(SECRET))
    }

    @ParameterizedTest
    @ValueSource(ints = [403, 413, 422, 429, 500])
    fun `maps documented HTTP failure statuses`(status: Int) {
        server.enqueue(MockResponse().setResponseCode(status).setBody("failure-$status"))

        val exception = assertThrows(LingyaApiException::class.java) {
            newClient().blockingForUser("user-1").createChat(AiChatInput("hello"))
        }

        assertEquals(status, exception.statusCode)
        assertEquals("failure-$status", exception.responseBody)
    }

    @Test
    fun `retries idempotent reads and never retries writes`() = runTest {
        server.enqueue(MockResponse().setResponseCode(503))
        server.enqueue(MockResponse().setResponseCode(200).addHeader("Content-Type", "application/json").setBody("{}"))
        val user = newClient(RetryPolicy(maxAttempts = 2)).forUser("user-1")

        user.apis.configuration.getAgentsConfig("channel").bodyOrThrow()

        assertEquals(2, server.requestCount)
        val firstNonce = server.takeRequest().headers["X-OpenAPI-Nonce"]
        val secondNonce = server.takeRequest().headers["X-OpenAPI-Nonce"]
        assertFalse(firstNonce == secondNonce, "every retry must be signed again")
    }

    @Test
    fun `decodes list and page response wrappers`() = runTest {
        server.enqueue(
            MockResponse().setResponseCode(200).addHeader("Content-Type", "application/json")
                .setBody("""{"records":[]}"""),
        )
        server.enqueue(
            MockResponse().setResponseCode(200).addHeader("Content-Type", "application/json")
                .setBody("""{"records":[],"page":{"current":0,"size":30,"total":0}}"""),
        )
        val user = newClient().forUser("user-1")

        val conversations = user.apis.conversations.listConversations("channel").bodyOrThrow()
        val messages = user.apis.messages.listConversationMessages("channel", "conversation-1").bodyOrThrow()

        assertEquals(0, conversations.records.size)
        assertEquals(30, messages.page.propertySize)
    }

    @Test
    fun `downloads binary export without JSON conversion`() = runTest {
        val bytes = byteArrayOf(0, 1, 2, 127, -1)
        server.enqueue(MockResponse().setResponseCode(200).setBody(okio.Buffer().write(bytes)))
        val user = newClient().forUser("user-1")

        val body = user.apis.sql.exportSqlQueryResult(
            "channel",
            "conversation-1",
            "result-1",
            ai.lingya.agents.sdk.generated.api.SQLApi.FormatExportSqlQueryResult.CSV,
        ).bodyOrThrow()

        assertTrue(bytes.contentEquals(body.bytes()))
    }

    @Test
    fun `propagates transport timeouts`() {
        server.enqueue(
            MockResponse().setResponseCode(201).setBodyDelay(500, java.util.concurrent.TimeUnit.MILLISECONDS)
                .setBody("{}"),
        )
        val transport = OkHttpClient.Builder().readTimeout(Duration.ofMillis(50)).build()
        val client = LingyaAgentsClient(
            server.url("/").toString(),
            "channel",
            OpenApiCredentials(ACCESS_KEY, SECRET),
            transport = transport,
        )

        assertThrows(SocketTimeoutException::class.java) {
            client.blockingForUser("user-1").createChat(AiChatInput("hello"))
        }
    }

    private fun newClient(retryPolicy: RetryPolicy = RetryPolicy.NONE): LingyaAgentsClient {
        var nonceIndex = 0
        return LingyaAgentsClient(
            baseUrl = server.url("/").toString(),
            channelId = "channel",
            credentials = OpenApiCredentials(ACCESS_KEY, SECRET),
            retryPolicy = retryPolicy,
            clock = Clock.fixed(Instant.ofEpochSecond(1735689600), ZoneOffset.UTC),
            nonceSource = NonceSource {
                nonceIndex += 1
                if (nonceIndex == 1) {
                    NONCE
                } else {
                    Base64.getUrlEncoder().withoutPadding().encodeToString(ByteArray(16) { nonceIndex.toByte() })
                }
            },
        )
    }

    private companion object {
        const val ACCESS_KEY = "abcdefghijklmnopqrstuvwxyzABCDEF"
        const val SECRET = "test-secret"
        const val NONCE = "MDEyMzQ1Njc4OWFiY2RlZg"
    }
}

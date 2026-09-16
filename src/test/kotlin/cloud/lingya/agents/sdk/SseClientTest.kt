package cloud.lingya.agents.sdk

import cloud.lingya.agents.sdk.event.AiChatBriefEvent
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withTimeout
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertInstanceOf
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import java.util.concurrent.TimeUnit

class SseClientTest {
    private val server = MockWebServer()

    @AfterEach
    fun closeServer() {
        server.close()
    }

    @Test
    fun `parses heartbeats multiline data unknown events and eof`() = runTest {
        server.enqueue(
            MockResponse()
                .setResponseCode(200)
                .addHeader("Content-Type", "text/event-stream")
                .setChunkedBody(
                    ": heartbeat\n\n" +
                        "data: {\"type\":\"message\",\"message\":\"hello\"}\n\n" +
                        "data: {\"type\":\n" +
                        "data: \"future-event\",\"payload\":{\"kept\":true}}\n\n" +
                        "data: {\"type\":\"error\",\"message\":\"recoverable\"}\n\n" +
                        "data: {\"type\":\"end\",\"executionTimeMillis\":1,\"totalUsage\":{\"inputTokens\":1,\"outputTokens\":1,\"totalTokens\":2},\"artifacts\":[],\"nonFileArtifacts\":[]}\n",
                    2,
                ),
        )

        val events = newClient().forUser("user-1")
            .streamChatEvents("conversation-1", "message-1")
            .toList()

        assertEquals(4, events.size)
        assertInstanceOf(AiChatBriefEvent.Message::class.java, events[0])
        val unknown = assertInstanceOf(AiChatBriefEvent.Unknown::class.java, events[1])
        assertEquals(true, unknown.rawJson.contains("\"kept\":true"))
        assertInstanceOf(AiChatBriefEvent.Error::class.java, events[2])
        assertInstanceOf(AiChatBriefEvent.End::class.java, events[3])
    }

    @Test
    fun `reports HTTP stream failures`() {
        server.enqueue(MockResponse().setResponseCode(429).setBody("rate limited"))
        val exception = assertThrows(ApiException::class.java) {
            newClient().blockingForUser("user-1").collectChatEvents("conversation-1", "message-1")
        }
        assertEquals(429, exception.statusCode)
    }

    @Test
    fun `cancels network call when flow collection stops`() = runBlocking {
        val stream =
            "data: {\"type\":\"message\",\"message\":\"first\"}\n\n" +
                "data: {\"type\":\"message\",\"message\":\"second\"}\n\n"
        server.enqueue(
            MockResponse().setResponseCode(200)
                .addHeader("Content-Type", "text/event-stream")
                .setChunkedBody(stream, 1)
                .throttleBody(256, 10, TimeUnit.MILLISECONDS),
        )

        val events = withTimeout(5_000) {
            newClient().forUser("user-1")
                .streamChatEvents("conversation-1", "message-1")
                .take(1)
                .toList()
        }

        assertEquals(1, events.size)
    }

    private fun newClient(): AgentsClient = AgentsClient(
        server.url("/").toString(),
        "channel",
        OpenApiCredentials("abcdefghijklmnopqrstuvwxyzABCDEF", "test-secret"),
        nonceSource = NonceSource { "MDEyMzQ1Njc4OWFiY2RlZg" },
    )
}

package ai.lingya.agents.sdk

import ai.lingya.agents.sdk.event.EndAiChatBriefEvent
import ai.lingya.agents.sdk.event.TextAiChatBriefEvent
import ai.lingya.agents.sdk.generated.model.AiChatInput
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assumptions.assumeTrue
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import java.util.UUID
import kotlin.time.Duration.Companion.seconds

@Tag("live")
class LiveApiIntegrationTest {
    @Test
    fun `真实服务接受签名并返回配置和分页响应`() = runBlocking {
        val fixture = liveFixture()

        val config = fixture.user.apis.configuration
            .getAgentsConfig(fixture.channelId)
            .bodyOrThrow()
        val conversations = fixture.user.apis.conversations
            .listConversations(fixture.channelId, current = 0, size = 1)
            .bodyOrThrow()

        assertTrue(config.modelConfig.modelKeyGroups.isNotEmpty())
        assertNotNull(conversations.records)
    }

    @Test
    fun `真实服务通过网关逐帧传输SSE探针`() = runBlocking {
        val fixture = liveFixture()
        val probeId = "sdk-${UUID.randomUUID()}"

        val events = withTimeout(10.seconds) {
            fixture.user.probeEventStream(probeId).toList()
        }

        assertEquals(listOf(0, 1, 2, 3), events.map { it.sequence })
        assertTrue(events.all { it.probeId == probeId })
        assertTrue(events.zipWithNext().all { (previous, next) ->
            next.serverElapsedMs > previous.serverElapsedMs
        })
    }

    @Test
    fun `真实服务创建对话并返回聊天事件`() = runBlocking {
        val fixture = liveFixture()
        val submission = fixture.user.createChat(AiChatInput(query = "仅回复英文 OK"))

        try {
            val events = withTimeout(120.seconds) {
                fixture.user.streamChatEvents(
                    conversationId = submission.conversationId,
                    messageId = submission.messageId,
                ).toList()
            }
            val message = fixture.user.apis.messages
                .getConversationMessage(
                    fixture.channelId,
                    submission.conversationId,
                    submission.messageId,
                )
                .bodyOrThrow()

            assertTrue(events.any { it is TextAiChatBriefEvent })
            assertTrue(events.any { it is EndAiChatBriefEvent })
            assertEquals(submission.messageId, message.messageId)
        } finally {
            val deletion = fixture.user.apis.conversations
                .deleteConversation(fixture.channelId, submission.conversationId)
            assertTrue(deletion.isSuccessful)
        }
    }

    private fun liveFixture(): LiveFixture {
        val baseUrl = requiredEnvironment("LINGYA_LIVE_BASE_URL")
        val channelId = requiredEnvironment("LINGYA_LIVE_CHANNEL_ID")
        val accessKey = requiredEnvironment("OPENAPI_AK")
        val secret = requiredEnvironment("OPENAPI_SK")
        val externalUserId = System.getenv("LINGYA_LIVE_EXTERNAL_USER_ID")
            ?.takeIf(String::isNotBlank)
            ?: "lingya-kotlin-sdk-live-smoke"
        val client = LingyaAgentsClient(
            baseUrl = baseUrl,
            channelId = channelId,
            credentials = OpenApiCredentials(accessKey, secret),
        )
        return LiveFixture(channelId, client.forUser(externalUserId))
    }

    private fun requiredEnvironment(name: String): String {
        val value = System.getenv(name)
        assumeTrue(!value.isNullOrBlank(), "$name is required for live API tests")
        return requireNotNull(value)
    }

    private data class LiveFixture(
        val channelId: String,
        val user: LingyaAgentsUserClient,
    )
}

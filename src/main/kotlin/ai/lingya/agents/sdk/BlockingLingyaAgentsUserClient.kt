package ai.lingya.agents.sdk

import ai.lingya.agents.sdk.event.AiChatBriefEvent
import ai.lingya.agents.sdk.generated.model.AiChatInput
import ai.lingya.agents.sdk.generated.model.AiChatSubmission
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking

/** Java-friendly blocking facade for common calls. */
public class BlockingLingyaAgentsUserClient internal constructor(
    private val delegate: LingyaAgentsUserClient,
) {
    /** Starts a chat and blocks the current thread until completion. */
    public fun createChat(input: AiChatInput): AiChatSubmission = runBlocking { delegate.createChat(input) }

    /** Continues a chat and blocks the current thread until completion. */
    public fun continueChat(conversationId: String, input: AiChatInput): AiChatSubmission =
        runBlocking { delegate.continueChat(conversationId, input) }

    /** Collects an SSE stream until normal EOF and returns all events. */
    @JvmOverloads
    public fun collectChatEvents(
        conversationId: String,
        messageId: String,
        requestId: String? = null,
    ): List<AiChatBriefEvent> = runBlocking {
        delegate.streamChatEvents(conversationId, messageId, requestId).toList()
    }

    /** Exposes generated APIs for Java callers that can bridge Kotlin suspend methods. */
    public fun getApis(): LingyaAgentsApis = delegate.apis
}

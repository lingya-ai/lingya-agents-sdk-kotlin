package ai.lingya.agents.sdk.event

import com.fasterxml.jackson.databind.JsonNode

/** Error reported inside a successfully established event stream. */
public data class ErrorAiChatBriefEvent(
    public val message: String?,
    override val raw: JsonNode,
) : AiChatBriefEvent {
    override val type: String = "error"
}

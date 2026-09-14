package ai.lingya.agents.sdk.event

import com.fasterxml.jackson.databind.JsonNode

/** Normal end marker from the event stream. */
public data class EndAiChatBriefEvent(
    public val status: String?,
    override val raw: JsonNode,
) : AiChatBriefEvent {
    override val type: String = "end"
}

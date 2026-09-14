package ai.lingya.agents.sdk.event

import com.fasterxml.jackson.databind.JsonNode

/** Text-bearing `message` or `think` event. */
public data class TextAiChatBriefEvent(
    override val type: String,
    public val message: String,
    override val raw: JsonNode,
) : AiChatBriefEvent

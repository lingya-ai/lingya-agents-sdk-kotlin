package ai.lingya.agents.sdk.event

import com.fasterxml.jackson.databind.JsonNode

/** Known non-text event whose complete original JSON remains available in [raw]. */
public data class KnownAiChatBriefEvent(
    override val type: String,
    override val raw: JsonNode,
) : AiChatBriefEvent

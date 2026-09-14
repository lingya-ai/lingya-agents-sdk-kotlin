package ai.lingya.agents.sdk.event

import com.fasterxml.jackson.databind.JsonNode

/** Event introduced by a newer server and preserved without data loss. */
public data class UnknownAiChatBriefEvent(
    override val type: String,
    override val raw: JsonNode,
) : AiChatBriefEvent

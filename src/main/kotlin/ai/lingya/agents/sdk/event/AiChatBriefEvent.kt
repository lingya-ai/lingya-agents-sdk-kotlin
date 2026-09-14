package ai.lingya.agents.sdk.event

import com.fasterxml.jackson.databind.JsonNode

/** Forward-compatible event emitted by a Lingya chat SSE stream. */
public sealed interface AiChatBriefEvent {
    /** Wire discriminator from the event JSON. */
    public val type: String

    /** Original event JSON, including fields unknown to this SDK version. */
    public val raw: JsonNode
}

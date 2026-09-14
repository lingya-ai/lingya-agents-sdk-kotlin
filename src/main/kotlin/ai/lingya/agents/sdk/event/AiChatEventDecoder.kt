package ai.lingya.agents.sdk.event

import com.fasterxml.jackson.databind.ObjectMapper

internal class AiChatEventDecoder(private val objectMapper: ObjectMapper) {
    fun decode(data: String): AiChatBriefEvent {
        val raw = objectMapper.readTree(data)
        val type = raw.path("type").takeIf { it.isTextual }?.asText().orEmpty()
        return when (type) {
            "message", "think" -> TextAiChatBriefEvent(type, raw.path("message").asText(""), raw)
            "error" -> ErrorAiChatBriefEvent(raw.path("message").takeIf { it.isTextual }?.asText(), raw)
            "end" -> EndAiChatBriefEvent(raw.path("status").takeIf { it.isTextual }?.asText(), raw)
            in KNOWN_EVENT_TYPES -> KnownAiChatBriefEvent(type, raw)
            else -> UnknownAiChatBriefEvent(type, raw)
        }
    }

    private companion object {
        val KNOWN_EVENT_TYPES = setOf(
            "start",
            "manual-interrupt",
            "chat-client-request",
            "chat-client-response",
            "compressor-context-start",
            "compressor-context-end",
            "tool-execution",
            "tool-execution-awaiting-user-input",
            "tool-execution-sub-agent-call",
            "user-query",
            "compactor-warning",
        )
    }
}

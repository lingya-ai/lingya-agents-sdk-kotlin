package ai.lingya.agents.sdk.event

import ai.lingya.agents.sdk.generated.model.AiChatAwaitingInputBriefEvent
import ai.lingya.agents.sdk.generated.model.AiChatCompactorWarningBriefEvent
import ai.lingya.agents.sdk.generated.model.AiChatCompressorContextEndBriefEvent
import ai.lingya.agents.sdk.generated.model.AiChatCompressorContextStartBriefEvent
import ai.lingya.agents.sdk.generated.model.AiChatEndBriefEvent
import ai.lingya.agents.sdk.generated.model.AiChatErrorBriefEvent
import ai.lingya.agents.sdk.generated.model.AiChatManualInterruptBriefEvent
import ai.lingya.agents.sdk.generated.model.AiChatMessageBriefEvent
import ai.lingya.agents.sdk.generated.model.AiChatRequestBriefEvent
import ai.lingya.agents.sdk.generated.model.AiChatResponseBriefEvent
import ai.lingya.agents.sdk.generated.model.AiChatStartBriefEvent
import ai.lingya.agents.sdk.generated.model.AiChatSubAgentCallBriefEvent
import ai.lingya.agents.sdk.generated.model.AiChatThinkBriefEvent
import ai.lingya.agents.sdk.generated.model.AiChatToolExecutionBriefEvent
import ai.lingya.agents.sdk.generated.model.AiChatUserQueryBriefEvent
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.ObjectMapper

/** 使用事件判别值选择具体 DTO，不把 JSON 降级为树或键值集合。 / Selects a concrete DTO without a JSON tree or key-value container. */
public class AiChatEventDecoder(public val objectMapper: ObjectMapper) {
    /** 解析一个完整事件 JSON。 / Decodes one complete event JSON value. */
    public fun decode(data: String): AiChatBriefEvent {
        val type = objectMapper.readValue(data, EventTypeEnvelope::class.java).type
        return when (type) {
            "user-query" -> AiChatBriefEvent.UserQuery(read(data))
            "compressor-context-start" -> AiChatBriefEvent.CompressorContextStart(read(data))
            "compressor-context-end" -> AiChatBriefEvent.CompressorContextEnd(read(data))
            "compactor-warning" -> AiChatBriefEvent.CompactorWarning(read(data))
            "manual-interrupt" -> AiChatBriefEvent.ManualInterrupt(read(data))
            "error" -> AiChatBriefEvent.Error(read(data))
            "start" -> AiChatBriefEvent.Start(read(data))
            "think" -> AiChatBriefEvent.Think(read(data))
            "chat-client-request" -> AiChatBriefEvent.ChatClientRequest(read(data))
            "chat-client-response" -> AiChatBriefEvent.ChatClientResponse(read(data))
            "message" -> AiChatBriefEvent.Message(read(data))
            "tool-execution" -> AiChatBriefEvent.ToolExecution(read(data))
            "tool-execution-sub-agent-call" -> AiChatBriefEvent.SubAgentCall(read(data))
            "tool-execution-awaiting-user-input" -> AiChatBriefEvent.AwaitingUserInput(read(data))
            "end" -> AiChatBriefEvent.End(read(data))
            else -> AiChatBriefEvent.Unknown(type, data)
        }
    }

    private inline fun <reified T> read(data: String): T = objectMapper.readValue(data, T::class.java)
}

private data class EventTypeEnvelope(val type: String = "")

internal class AiChatBriefEventDeserializer : JsonDeserializer<AiChatBriefEvent>() {
    override fun deserialize(parser: JsonParser, context: DeserializationContext): AiChatBriefEvent {
        val document = parser.captureRawJson(context)
        return AiChatEventDecoder(document.objectMapper).decode(document.rawJson)
    }
}

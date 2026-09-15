package cloud.lingya.agents.sdk.event

import cloud.lingya.agents.sdk.generated.model.AssistantChatMessage
import cloud.lingya.agents.sdk.generated.model.SystemChatMessage
import cloud.lingya.agents.sdk.generated.model.ToolResponseChatMessage
import cloud.lingya.agents.sdk.generated.model.UserChatMessage
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

/** 模型请求或响应中的强类型消息。 / Strongly typed message in a model request or response. */
@JsonDeserialize(using = ChatMessageDeserializer::class)
public sealed interface ChatMessage {
    /** 消息角色判别值。 / Message-role discriminator. */
    public val type: String

    /** 系统消息。 / System message. */
    public data class System(public val value: SystemChatMessage) : ChatMessage { override val type: String = "SYSTEM" }
    /** 用户消息。 / User message. */
    public data class User(public val value: UserChatMessage) : ChatMessage { override val type: String = "USER" }
    /** 助手消息。 / Assistant message. */
    public data class Assistant(public val value: AssistantChatMessage) : ChatMessage { override val type: String = "ASSISTANT" }
    /** 工具响应消息。 / Tool-response message. */
    public data class Tool(public val value: ToolResponseChatMessage) : ChatMessage { override val type: String = "TOOL" }
    /** 未知消息角色。 / Unknown future message role. */
    public data class Unknown(override val type: String, public val rawJson: String) : ChatMessage
}

internal class ChatMessageDeserializer : JsonDeserializer<ChatMessage>() {
    override fun deserialize(parser: JsonParser, context: DeserializationContext): ChatMessage {
        val document = parser.captureRawJson(context)
        return decodeChatMessage(document.objectMapper, document.rawJson)
    }
}

private fun decodeChatMessage(mapper: ObjectMapper, rawJson: String): ChatMessage {
    val type = mapper.readValue(rawJson, MessageTypeEnvelope::class.java).type.uppercase()
    return when (type) {
        "SYSTEM" -> ChatMessage.System(mapper.readValue(rawJson, SystemChatMessage::class.java))
        "USER" -> ChatMessage.User(mapper.readValue(rawJson, UserChatMessage::class.java))
        "ASSISTANT" -> ChatMessage.Assistant(mapper.readValue(rawJson, AssistantChatMessage::class.java))
        "TOOL" -> ChatMessage.Tool(mapper.readValue(rawJson, ToolResponseChatMessage::class.java))
        else -> ChatMessage.Unknown(type, rawJson)
    }
}

private data class MessageTypeEnvelope(val type: String = "")

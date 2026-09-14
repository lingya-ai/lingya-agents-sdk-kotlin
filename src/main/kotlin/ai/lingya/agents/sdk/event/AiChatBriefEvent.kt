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
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

/**
 * 聊天执行过程中产生的强类型事件。 / Strongly typed event emitted during chat execution.
 *
 * 普通事件查询、批量事件查询和 SSE 使用同一类型族。服务端新增未知事件时，SDK 返回 [Unknown] 并保留原始 JSON。
 * The JSON, batch, and SSE APIs share this model. [Unknown] preserves a future event without weakening known models.
 */
@JsonDeserialize(using = AiChatBriefEventDeserializer::class)
public sealed interface AiChatBriefEvent {
    /** 线上事件判别值。 / Wire event discriminator. */
    public val type: String

    /** 用户原始问题事件。 / Original user-query event. */
    public data class UserQuery(public val value: AiChatUserQueryBriefEvent) : AiChatBriefEvent { override val type: String = "user-query" }

    /** 上下文压缩开始事件。 / Context-compression start event. */
    public data class CompressorContextStart(public val value: AiChatCompressorContextStartBriefEvent) : AiChatBriefEvent { override val type: String = "compressor-context-start" }

    /** 上下文压缩结束事件。 / Context-compression end event. */
    public data class CompressorContextEnd(public val value: AiChatCompressorContextEndBriefEvent) : AiChatBriefEvent { override val type: String = "compressor-context-end" }

    /** 上下文压缩警告事件。 / Context-compaction warning event. */
    public data class CompactorWarning(public val value: AiChatCompactorWarningBriefEvent) : AiChatBriefEvent { override val type: String = "compactor-warning" }

    /** 手动中断事件。 / Manual-interruption event. */
    public data class ManualInterrupt(public val value: AiChatManualInterruptBriefEvent) : AiChatBriefEvent { override val type: String = "manual-interrupt" }

    /** 执行错误事件。 / Execution error event. */
    public data class Error(public val value: AiChatErrorBriefEvent) : AiChatBriefEvent { override val type: String = "error" }

    /** 执行开始事件。 / Execution start event. */
    public data class Start(public val value: AiChatStartBriefEvent) : AiChatBriefEvent { override val type: String = "start" }

    /** 模型思考文本事件。 / Model reasoning-text event. */
    public data class Think(public val value: AiChatThinkBriefEvent) : AiChatBriefEvent { override val type: String = "think" }

    /** 发往模型的请求快照。 / Request snapshot sent to the model. */
    public data class ChatClientRequest(public val value: AiChatRequestBriefEvent) : AiChatBriefEvent { override val type: String = "chat-client-request" }

    /** 模型响应快照。 / Model response snapshot. */
    public data class ChatClientResponse(public val value: AiChatResponseBriefEvent) : AiChatBriefEvent { override val type: String = "chat-client-response" }

    /** 助手可见文本事件。 / User-visible assistant message event. */
    public data class Message(public val value: AiChatMessageBriefEvent) : AiChatBriefEvent { override val type: String = "message" }

    /** 工具执行状态事件。 / Tool-execution status event. */
    public data class ToolExecution(public val value: AiChatToolExecutionBriefEvent) : AiChatBriefEvent { override val type: String = "tool-execution" }

    /** 子智能体调用事件。 / Sub-agent invocation event. */
    public data class SubAgentCall(public val value: AiChatSubAgentCallBriefEvent) : AiChatBriefEvent { override val type: String = "tool-execution-sub-agent-call" }

    /** 等待用户回答的工具事件。 / Tool event waiting for user input. */
    public data class AwaitingUserInput(public val value: AiChatAwaitingInputBriefEvent) : AiChatBriefEvent { override val type: String = "tool-execution-awaiting-user-input" }

    /** 消息执行结束事件。 / Message-execution completion event. */
    public data class End(public val value: AiChatEndBriefEvent) : AiChatBriefEvent { override val type: String = "end" }

    /**
     * 当前 SDK 尚不认识的未来事件。 / Future event unknown to this SDK version.
     *
     * [rawJson] 是完整事件对象，调用方可记录或转交给更新后的解析器。 / [rawJson] contains the complete event object.
     */
    public data class Unknown(override val type: String, public val rawJson: String) : AiChatBriefEvent
}

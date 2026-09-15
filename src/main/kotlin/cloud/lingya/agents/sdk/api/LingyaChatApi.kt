package cloud.lingya.agents.sdk.api

import cloud.lingya.agents.sdk.LingyaAgentsUserClient
import cloud.lingya.agents.sdk.bodyOrThrow
import cloud.lingya.agents.sdk.generated.api.ChatApi
import cloud.lingya.agents.sdk.generated.model.*
import kotlinx.coroutines.flow.Flow

/**
 * chat 分组的 channel 绑定异步接口。 / Channel-bound asynchronous chat operations.
 *
 * `channelId` 来自根客户端，避免调用方法时传入与签名目标不一致的 channel。
 * / `channelId` comes from the root client so method calls cannot diverge from the signed channel.
 *
 * @author 思追(shaco)
 */
public class LingyaChatApi internal constructor(
    private val channelId: String,
    private val delegate: ChatApi,
    private val userClient: LingyaAgentsUserClient,
) {
    /**
     * 创建会话并提交消息 / Create a conversation and submit a message
     *
     * @param input 创建会话并提交消息 / Create a conversation and submit a message 的强类型请求体。 / Typed request body for createChat.
     * @return 契约定义的强类型响应。 / The typed response defined by the contract.
     */
    public suspend fun createChat(
        input: AiChatInput,
    ): AiChatSubmission =
        delegate.createChat(channelId, input).bodyOrThrow()

    /**
     * 向已有会话提交消息 / Submit a message to an existing conversation
     *
     * @param conversationId 会话 ID；必须属于当前外部用户。 / Conversation ID owned by the current external user.
     * @param input 向已有会话提交消息 / Submit a message to an existing conversation 的强类型请求体。 / Typed request body for continueChat.
     * @return 契约定义的强类型响应。 / The typed response defined by the contract.
     */
    public suspend fun continueChat(
        conversationId: kotlin.String,
        input: AiChatInput,
    ): AiChatSubmission =
        delegate.continueChat(channelId, conversationId, input).bodyOrThrow()

    /**
     * 订阅消息事件流 / Stream message events
     *
     * @param conversationId 会话 ID；必须属于当前外部用户。 / Conversation ID owned by the current external user.
     * @param xRequestID 可选诊断请求 ID，便于关联客户端与服务端日志。 / Optional diagnostic request ID used to correlate client and server logs.
     * @param input 订阅消息事件流 / Stream message events 的强类型请求体。 / Typed request body for streamChatEvents.
     * @return 契约定义的强类型响应。 / The typed response defined by the contract.
     */
    public fun streamChatEvents(
        conversationId: kotlin.String,
        input: AiChatStreamInput,
        xRequestID: kotlin.String? = null,
    ): Flow<cloud.lingya.agents.sdk.event.AiChatBriefEvent> =
        userClient.streamChatEventsInternal(conversationId, input, xRequestID)

    /**
     * 探测 SSE 连接 / Probe the SSE connection
     *
     * @param xRequestID 可选诊断请求 ID，便于关联客户端与服务端日志。 / Optional diagnostic request ID used to correlate client and server logs.
     * @param input 探测 SSE 连接 / Probe the SSE connection 的强类型请求体。 / Typed request body for probeEventStream.
     * @return 契约定义的强类型响应。 / The typed response defined by the contract.
     */
    public fun probeEventStream(
        input: ChatStreamProbeInput,
        xRequestID: kotlin.String? = null,
    ): Flow<ChatStreamProbeEvent> =
        userClient.probeEventStreamInternal(input, xRequestID)

    /**
     * 中断会话执行 / Interrupt conversation execution
     *
     * @param conversationId 会话 ID；必须属于当前外部用户。 / Conversation ID owned by the current external user.
     * @return 契约定义的强类型响应。 / The typed response defined by the contract.
     */
    public suspend fun interruptConversation(
        conversationId: kotlin.String,
    ): Unit =
        delegate.interruptConversation(channelId, conversationId).bodyOrThrow()

    /**
     * 压缩会话上下文 / Compact conversation context
     *
     * @param conversationId 会话 ID；必须属于当前外部用户。 / Conversation ID owned by the current external user.
     * @param force 是否忽略当前阈值并强制压缩。 / Whether to compact regardless of the current threshold.
     * @return 契约定义的强类型响应。 / The typed response defined by the contract.
     */
    public suspend fun compactConversation(
        conversationId: kotlin.String,
        force: kotlin.Boolean? = true,
    ): Unit =
        delegate.compactConversation(channelId, conversationId, force).bodyOrThrow()

}

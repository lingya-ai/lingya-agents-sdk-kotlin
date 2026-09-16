package cloud.lingya.agents.sdk.generated.api

import cloud.lingya.agents.sdk.generated.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.fasterxml.jackson.annotation.JsonProperty

import cloud.lingya.agents.sdk.generated.model.AiChatInput
import cloud.lingya.agents.sdk.generated.model.AiChatStreamInput
import cloud.lingya.agents.sdk.generated.model.AiChatSubmission
import cloud.lingya.agents.sdk.generated.model.ChatStreamProbeEvent
import cloud.lingya.agents.sdk.generated.model.ChatStreamProbeInput
import cloud.lingya.agents.sdk.generated.model.CodeMessage
import cloud.lingya.agents.sdk.generated.model.ValidationError

interface ChatApi {
    /**
     * POST api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/compact
     * 压缩会话上下文 / Compact conversation context
     * ### 使用场景 上下文接近模型上限或用户要求时触发压缩。  ### Use case Trigger context compaction near the model limit or on explicit user request.  ### 前置条件 渠道凭证和外部用户已配置，目标资源属于当前用户。  ### Prerequisites Channel credentials and the external user are configured, and the target resource belongs to that user.  ### 行为与副作用 异步安排上下文压缩。  ### Behavior and side effects Schedules asynchronous context compaction.  ### 后续调用 轮询上下文占用或等待相关事件。  ### Next step Poll context usage or wait for related events.  ### 接口摘要 压缩会话上下文 / Compact conversation context  ### Operation summary 压缩会话上下文 / Compact conversation context
     * Responses:
     *  - 202: 压缩会话上下文 / Compact conversation context 的成功响应。 / Successful response for compactConversation.
     *  - 401: 接口错误。 / API error.
     *  - 403: 接口错误。 / API error.
     *  - 413: 接口错误。 / API error.
     *  - 422: 参数校验错误。 / Validation error.
     *  - 429: 接口错误。 / API error.
     *  - 500: 接口错误。 / API error.
     *  - 503: 接口错误。 / API error.
     *
     * @param channelId Agent OpenAPI 渠道 UUID。 / Agent OpenAPI channel UUID.
     * @param conversationId 会话 ID；必须属于当前外部用户。 / Conversation ID owned by the current external user.
     * @param force 是否忽略当前阈值并强制压缩。 / Whether to compact regardless of the current threshold. (optional, default to true)
     * @return [Unit]
     */
    @POST("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/compact")
    suspend fun compactConversation(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Query("force") force: kotlin.Boolean? = true): Response<Unit>

    /**
     * POST api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}
     * 向已有会话提交消息 / Submit a message to an existing conversation
     * ### 使用场景 在已有会话中提交下一轮用户消息。  ### Use case Submit the next user turn to an existing conversation.  ### 前置条件 渠道凭证和外部用户已配置，目标资源属于当前用户。  ### Prerequisites Channel credentials and the external user are configured, and the target resource belongs to that user.  ### 行为与副作用 创建新消息并安排继续执行。  ### Behavior and side effects Creates a new message and schedules continued execution.  ### 后续调用 订阅该 messageId 的 SSE 或稍后补取事件。  ### Next step Subscribe to SSE for the messageId or recover its events later.  ### 接口摘要 向已有会话提交消息 / Submit a message to an existing conversation  ### Operation summary 向已有会话提交消息 / Submit a message to an existing conversation
     * Responses:
     *  - 201: 向已有会话提交消息 / Submit a message to an existing conversation 的成功响应。 / Successful response for continueChat.
     *  - 401: 接口错误。 / API error.
     *  - 403: 接口错误。 / API error.
     *  - 413: 接口错误。 / API error.
     *  - 422: 参数校验错误。 / Validation error.
     *  - 429: 接口错误。 / API error.
     *  - 500: 接口错误。 / API error.
     *  - 503: 接口错误。 / API error.
     *
     * @param channelId Agent OpenAPI 渠道 UUID。 / Agent OpenAPI channel UUID.
     * @param conversationId 会话 ID；必须属于当前外部用户。 / Conversation ID owned by the current external user.
     * @param aiChatInput 向已有会话提交消息 / Submit a message to an existing conversation 的 JSON 请求参数。 / JSON request parameters for continueChat.
     * @return [AiChatSubmission]
     */
    @POST("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}")
    suspend fun continueChat(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Body aiChatInput: AiChatInput): Response<AiChatSubmission>

    /**
     * POST api/agents/channel/openapi/v1/{channelId}/chat
     * 创建会话并提交消息 / Create a conversation and submit a message
     * ### 使用场景 创建新会话并提交第一条用户消息。  ### Use case Create a conversation and submit its first user message.  ### 前置条件 已读取配置；引用的文件已经确认上传。  ### Prerequisites Configuration has been read and referenced files are confirmed uploads.  ### 行为与副作用 创建会话和消息，并安排 Agent 执行。  ### Behavior and side effects Creates a conversation and message and schedules Agent execution.  ### 后续调用 使用返回的 conversationId 和 messageId 订阅 SSE。  ### Next step Use the returned conversationId and messageId to subscribe to SSE.  ### 接口摘要 创建会话并提交消息 / Create a conversation and submit a message  ### Operation summary 创建会话并提交消息 / Create a conversation and submit a message
     * Responses:
     *  - 201: 创建会话并提交消息 / Create a conversation and submit a message 的成功响应。 / Successful response for createChat.
     *  - 401: 接口错误。 / API error.
     *  - 403: 接口错误。 / API error.
     *  - 413: 接口错误。 / API error.
     *  - 422: 参数校验错误。 / Validation error.
     *  - 429: 接口错误。 / API error.
     *  - 500: 接口错误。 / API error.
     *  - 503: 接口错误。 / API error.
     *
     * @param channelId Agent OpenAPI 渠道 UUID。 / Agent OpenAPI channel UUID.
     * @param aiChatInput 创建会话并提交消息 / Create a conversation and submit a message 的 JSON 请求参数。 / JSON request parameters for createChat.
     * @return [AiChatSubmission]
     */
    @POST("api/agents/channel/openapi/v1/{channelId}/chat")
    suspend fun createChat(@Path("channelId") channelId: kotlin.String, @Body aiChatInput: AiChatInput): Response<AiChatSubmission>

    /**
     * DELETE api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/interrupt
     * 中断会话执行 / Interrupt conversation execution
     * ### 使用场景 用户主动停止正在执行的会话。  ### Use case Stop an actively executing conversation at the user&#39;s request.  ### 前置条件 渠道凭证和外部用户已配置，目标资源属于当前用户。  ### Prerequisites Channel credentials and the external user are configured, and the target resource belongs to that user.  ### 行为与副作用 请求终止当前执行；已完成输出不会回滚。  ### Behavior and side effects Requests termination of active execution; completed output is not rolled back.  ### 后续调用 查询活动状态或消息详情确认终态。  ### Next step Query activity or message detail to confirm the terminal state.  ### 接口摘要 中断会话执行 / Interrupt conversation execution  ### Operation summary 中断会话执行 / Interrupt conversation execution
     * Responses:
     *  - 200: 中断会话执行 / Interrupt conversation execution 的成功响应。 / Successful response for interruptConversation.
     *  - 401: 接口错误。 / API error.
     *  - 403: 接口错误。 / API error.
     *  - 413: 接口错误。 / API error.
     *  - 422: 参数校验错误。 / Validation error.
     *  - 429: 接口错误。 / API error.
     *  - 500: 接口错误。 / API error.
     *  - 503: 接口错误。 / API error.
     *
     * @param channelId Agent OpenAPI 渠道 UUID。 / Agent OpenAPI channel UUID.
     * @param conversationId 会话 ID；必须属于当前外部用户。 / Conversation ID owned by the current external user.
     * @return [Unit]
     */
    @DELETE("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/interrupt")
    suspend fun interruptConversation(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String): Response<Unit>

}

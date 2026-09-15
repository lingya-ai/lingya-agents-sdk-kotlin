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
     * 压缩会话上下文 / Compact conversation context 请求会在身份验签和资源归属校验后执行；响应字段以本契约为准。 / The request runs after signature and resource-ownership validation; this contract defines the response fields.
     * Responses:
     *  - 202: 压缩会话上下文 / Compact conversation context 的成功响应。 / Successful response for compactConversation.
     *  - 401: API error
     *  - 403: API error
     *  - 413: API error
     *  - 422: Validation error
     *  - 429: API error
     *  - 500: API error
     *  - 503: API error
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
     * 向已有会话提交消息 / Submit a message to an existing conversation 请求会在身份验签和资源归属校验后执行；响应字段以本契约为准。 / The request runs after signature and resource-ownership validation; this contract defines the response fields.
     * Responses:
     *  - 201: 向已有会话提交消息 / Submit a message to an existing conversation 的成功响应。 / Successful response for continueChat.
     *  - 401: API error
     *  - 403: API error
     *  - 413: API error
     *  - 422: Validation error
     *  - 429: API error
     *  - 500: API error
     *  - 503: API error
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
     * 创建会话并提交消息 / Create a conversation and submit a message 请求会在身份验签和资源归属校验后执行；响应字段以本契约为准。 / The request runs after signature and resource-ownership validation; this contract defines the response fields.
     * Responses:
     *  - 201: 创建会话并提交消息 / Create a conversation and submit a message 的成功响应。 / Successful response for createChat.
     *  - 401: API error
     *  - 403: API error
     *  - 413: API error
     *  - 422: Validation error
     *  - 429: API error
     *  - 500: API error
     *  - 503: API error
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
     * 中断会话执行 / Interrupt conversation execution 请求会在身份验签和资源归属校验后执行；响应字段以本契约为准。 / The request runs after signature and resource-ownership validation; this contract defines the response fields.
     * Responses:
     *  - 200: 中断会话执行 / Interrupt conversation execution 的成功响应。 / Successful response for interruptConversation.
     *  - 401: API error
     *  - 403: API error
     *  - 413: API error
     *  - 422: Validation error
     *  - 429: API error
     *  - 500: API error
     *  - 503: API error
     *
     * @param channelId Agent OpenAPI 渠道 UUID。 / Agent OpenAPI channel UUID.
     * @param conversationId 会话 ID；必须属于当前外部用户。 / Conversation ID owned by the current external user.
     * @return [Unit]
     */
    @DELETE("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/interrupt")
    suspend fun interruptConversation(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String): Response<Unit>

}

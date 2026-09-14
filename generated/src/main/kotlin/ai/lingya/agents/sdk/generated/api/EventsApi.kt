package ai.lingya.agents.sdk.generated.api

import ai.lingya.agents.sdk.generated.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.fasterxml.jackson.annotation.JsonProperty

import ai.lingya.agents.sdk.generated.model.AiChatBriefEventList
import ai.lingya.agents.sdk.generated.model.AiChatEventsBatch
import ai.lingya.agents.sdk.generated.model.AiChatEventsBatchInput
import ai.lingya.agents.sdk.generated.model.CodeMessage
import ai.lingya.agents.sdk.generated.model.ValidationError

interface EventsApi {
    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/events
     * 读取消息事件 / Get message events
     * 读取消息事件 / Get message events 请求会在身份验签和资源归属校验后执行；响应字段以本契约为准。 / The request runs after signature and resource-ownership validation; this contract defines the response fields.
     * Responses:
     *  - 200: 读取消息事件 / Get message events 的成功响应。 / Successful response for getChatEvents.
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
     * @param messageId 用户消息 ID；必须属于指定会话。 / User-message ID owned by the specified conversation.
     * @return [AiChatBriefEventList]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/events")
    suspend fun getChatEvents(@Path("channelId") channelId: kotlin.String, @Query("conversationId") conversationId: kotlin.String, @Query("messageId") messageId: kotlin.String): Response<AiChatBriefEventList>

    /**
     * POST api/agents/channel/openapi/v1/{channelId}/chat/events/batch
     * 批量读取消息事件 / Get message events in batch
     * 批量读取消息事件 / Get message events in batch 请求会在身份验签和资源归属校验后执行；响应字段以本契约为准。 / The request runs after signature and resource-ownership validation; this contract defines the response fields.
     * Responses:
     *  - 201: 批量读取消息事件 / Get message events in batch 的成功响应。 / Successful response for getChatEventsBatch.
     *  - 401: API error
     *  - 403: API error
     *  - 413: API error
     *  - 422: Validation error
     *  - 429: API error
     *  - 500: API error
     *  - 503: API error
     *
     * @param channelId Agent OpenAPI 渠道 UUID。 / Agent OpenAPI channel UUID.
     * @param aiChatEventsBatchInput 批量读取消息事件 / Get message events in batch 的 JSON 请求参数。 / JSON request parameters for getChatEventsBatch.
     * @return [AiChatEventsBatch]
     */
    @POST("api/agents/channel/openapi/v1/{channelId}/chat/events/batch")
    suspend fun getChatEventsBatch(@Path("channelId") channelId: kotlin.String, @Body aiChatEventsBatchInput: AiChatEventsBatchInput): Response<AiChatEventsBatch>

}

package cloud.lingya.agents.sdk.generated.api

import cloud.lingya.agents.sdk.generated.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.fasterxml.jackson.annotation.JsonProperty

import cloud.lingya.agents.sdk.generated.model.AiChatBriefEventList
import cloud.lingya.agents.sdk.generated.model.AiChatEventsBatch
import cloud.lingya.agents.sdk.generated.model.AiChatEventsBatchInput
import cloud.lingya.agents.sdk.generated.model.CodeMessage
import cloud.lingya.agents.sdk.generated.model.ValidationError

interface EventsApi {
    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/events
     * 读取消息事件 / Get message events
     * ### 使用场景 恢复单条消息已经持久化的事件。  ### Use case Recover persisted events for one message.  ### 前置条件 conversationId 与 messageId 匹配。  ### Prerequisites conversationId and messageId match.  ### 行为与副作用 只读，不重新执行 Agent。  ### Behavior and side effects Read-only and does not re-run the Agent.  ### 后续调用 重建 UI 状态或与 SSE 后续事件合并。  ### Next step Rebuild UI state or merge with later SSE events.  ### 接口摘要 读取消息事件 / Get message events  ### Operation summary 读取消息事件 / Get message events
     * Responses:
     *  - 200: 读取消息事件 / Get message events 的成功响应。 / Successful response for getChatEvents.
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
     * @param messageId 用户消息 ID；必须属于指定会话。 / User-message ID owned by the specified conversation.
     * @return [AiChatBriefEventList]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/events")
    suspend fun getChatEvents(@Path("channelId") channelId: kotlin.String, @Query("conversationId") conversationId: kotlin.String, @Query("messageId") messageId: kotlin.String): Response<AiChatBriefEventList>

    /**
     * POST api/agents/channel/openapi/v1/{channelId}/chat/events/batch
     * 批量读取消息事件 / Get message events in batch
     * ### 使用场景 客户端重启后一次恢复多条消息事件。  ### Use case Recover events for multiple messages after a client restart.  ### 前置条件 请求包含一个会话和最多 50 个消息 ID。  ### Prerequisites The request contains one conversation and up to 50 message IDs.  ### 行为与副作用 只读，并明确返回跳过项。  ### Behavior and side effects Read-only and explicitly reports skipped items.  ### 后续调用 按 messageId 合并记录并处理 skipped。  ### Next step Merge records by messageId and handle skipped entries.  ### 接口摘要 批量读取消息事件 / Get message events in batch  ### Operation summary 批量读取消息事件 / Get message events in batch
     * Responses:
     *  - 201: 批量读取消息事件 / Get message events in batch 的成功响应。 / Successful response for getChatEventsBatch.
     *  - 401: 接口错误。 / API error.
     *  - 403: 接口错误。 / API error.
     *  - 413: 接口错误。 / API error.
     *  - 422: 参数校验错误。 / Validation error.
     *  - 429: 接口错误。 / API error.
     *  - 500: 接口错误。 / API error.
     *  - 503: 接口错误。 / API error.
     *
     * @param channelId Agent OpenAPI 渠道 UUID。 / Agent OpenAPI channel UUID.
     * @param aiChatEventsBatchInput 批量读取消息事件 / Get message events in batch 的 JSON 请求参数。 / JSON request parameters for getChatEventsBatch.
     * @return [AiChatEventsBatch]
     */
    @POST("api/agents/channel/openapi/v1/{channelId}/chat/events/batch")
    suspend fun getChatEventsBatch(@Path("channelId") channelId: kotlin.String, @Body aiChatEventsBatchInput: AiChatEventsBatchInput): Response<AiChatEventsBatch>

}

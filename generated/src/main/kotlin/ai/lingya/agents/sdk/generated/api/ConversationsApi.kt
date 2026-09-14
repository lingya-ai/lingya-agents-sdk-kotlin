package ai.lingya.agents.sdk.generated.api

import ai.lingya.agents.sdk.generated.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.fasterxml.jackson.annotation.JsonProperty

import ai.lingya.agents.sdk.generated.model.CodeMessage
import ai.lingya.agents.sdk.generated.model.ConversationActivityBatchInput
import ai.lingya.agents.sdk.generated.model.ConversationActivityList
import ai.lingya.agents.sdk.generated.model.ConversationContextUsage
import ai.lingya.agents.sdk.generated.model.ConversationIds
import ai.lingya.agents.sdk.generated.model.ConversationReadReceipt
import ai.lingya.agents.sdk.generated.model.ConversationReadReceiptInput
import ai.lingya.agents.sdk.generated.model.ConversationShareCreated
import ai.lingya.agents.sdk.generated.model.ConversationShareInput
import ai.lingya.agents.sdk.generated.model.ConversationShareList
import ai.lingya.agents.sdk.generated.model.ConversationShareRevoked
import ai.lingya.agents.sdk.generated.model.ConversationStats
import ai.lingya.agents.sdk.generated.model.ConversationStatusInput
import ai.lingya.agents.sdk.generated.model.ConversationSummaryList
import ai.lingya.agents.sdk.generated.model.ConversationTitle
import ai.lingya.agents.sdk.generated.model.ConversationTitleInput
import ai.lingya.agents.sdk.generated.model.ValidationError

interface ConversationsApi {
    /**
     * POST api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/shares
     * 创建会话分享 / Create a conversation share
     * 创建会话分享 / Create a conversation share 请求会在身份验签和资源归属校验后执行；响应字段以本契约为准。 / The request runs after signature and resource-ownership validation; this contract defines the response fields.
     * Responses:
     *  - 201: 创建会话分享 / Create a conversation share 的成功响应。 / Successful response for createConversationShare.
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
     * @param conversationShareInput 创建会话分享 / Create a conversation share 的 JSON 请求参数。 / JSON request parameters for createConversationShare.
     * @return [ConversationShareCreated]
     */
    @POST("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/shares")
    suspend fun createConversationShare(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Body conversationShareInput: ConversationShareInput): Response<ConversationShareCreated>

    /**
     * DELETE api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}
     * 删除会话 / Delete a conversation
     * 删除会话 / Delete a conversation 请求会在身份验签和资源归属校验后执行；响应字段以本契约为准。 / The request runs after signature and resource-ownership validation; this contract defines the response fields.
     * Responses:
     *  - 200: 删除会话 / Delete a conversation 的成功响应。 / Successful response for deleteConversation.
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
    @DELETE("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}")
    suspend fun deleteConversation(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String): Response<Unit>

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/context-usage
     * 读取上下文占用 / Get context usage
     * 读取上下文占用 / Get context usage 请求会在身份验签和资源归属校验后执行；响应字段以本契约为准。 / The request runs after signature and resource-ownership validation; this contract defines the response fields.
     * Responses:
     *  - 200: 读取上下文占用 / Get context usage 的成功响应。 / Successful response for getConversationContextUsage.
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
     * @return [ConversationContextUsage]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/context-usage")
    suspend fun getConversationContextUsage(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String): Response<ConversationContextUsage>

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations/stats
     * 读取会话统计 / Get conversation statistics
     * 读取会话统计 / Get conversation statistics 请求会在身份验签和资源归属校验后执行；响应字段以本契约为准。 / The request runs after signature and resource-ownership validation; this contract defines the response fields.
     * Responses:
     *  - 200: 读取会话统计 / Get conversation statistics 的成功响应。 / Successful response for getConversationStats.
     *  - 401: API error
     *  - 403: API error
     *  - 413: API error
     *  - 422: Validation error
     *  - 429: API error
     *  - 500: API error
     *  - 503: API error
     *
     * @param channelId Agent OpenAPI 渠道 UUID。 / Agent OpenAPI channel UUID.
     * @return [ConversationStats]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/stats")
    suspend fun getConversationStats(@Path("channelId") channelId: kotlin.String): Response<ConversationStats>

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/title
     * 读取会话标题 / Get conversation title
     * 读取会话标题 / Get conversation title 请求会在身份验签和资源归属校验后执行；响应字段以本契约为准。 / The request runs after signature and resource-ownership validation; this contract defines the response fields.
     * Responses:
     *  - 200: 读取会话标题 / Get conversation title 的成功响应。 / Successful response for getConversationTitle.
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
     * @return [ConversationTitle]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/title")
    suspend fun getConversationTitle(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String): Response<ConversationTitle>

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations/active
     * 查询活动会话 / List active conversations
     * 查询活动会话 / List active conversations 请求会在身份验签和资源归属校验后执行；响应字段以本契约为准。 / The request runs after signature and resource-ownership validation; this contract defines the response fields.
     * Responses:
     *  - 200: 查询活动会话 / List active conversations 的成功响应。 / Successful response for listActiveConversations.
     *  - 401: API error
     *  - 403: API error
     *  - 413: API error
     *  - 422: Validation error
     *  - 429: API error
     *  - 500: API error
     *  - 503: API error
     *
     * @param channelId Agent OpenAPI 渠道 UUID。 / Agent OpenAPI channel UUID.
     * @return [ConversationIds]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/active")
    suspend fun listActiveConversations(@Path("channelId") channelId: kotlin.String): Response<ConversationIds>

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/shares
     * 查询会话分享 / List conversation shares
     * 查询会话分享 / List conversation shares 请求会在身份验签和资源归属校验后执行；响应字段以本契约为准。 / The request runs after signature and resource-ownership validation; this contract defines the response fields.
     * Responses:
     *  - 200: 查询会话分享 / List conversation shares 的成功响应。 / Successful response for listConversationShares.
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
     * @return [ConversationShareList]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/shares")
    suspend fun listConversationShares(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String): Response<ConversationShareList>


    /**
    * enum for parameter orderDirection
    */
    enum class OrderDirectionListConversations(val value: kotlin.String) {
            @JsonProperty(value = "ASC") ASC("ASC"),
            @JsonProperty(value = "DESC") DESC("DESC"),
    }


    /**
    * enum for parameter orderNullHandling
    */
    enum class OrderNullHandlingListConversations(val value: kotlin.String) {
            @JsonProperty(value = "NATIVE") NATIVE("NATIVE"),
            @JsonProperty(value = "NULLS_FIRST") NULLS_FIRST("NULLS_FIRST"),
            @JsonProperty(value = "NULLS_LAST") NULLS_LAST("NULLS_LAST"),
    }

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations
     * 分页查询会话 / List conversations
     * 分页查询会话 / List conversations 请求会在身份验签和资源归属校验后执行；响应字段以本契约为准。 / The request runs after signature and resource-ownership validation; this contract defines the response fields.
     * Responses:
     *  - 200: 分页查询会话 / List conversations 的成功响应。 / Successful response for listConversations.
     *  - 401: API error
     *  - 403: API error
     *  - 413: API error
     *  - 422: Validation error
     *  - 429: API error
     *  - 500: API error
     *  - 503: API error
     *
     * @param channelId Agent OpenAPI 渠道 UUID。 / Agent OpenAPI channel UUID.
     * @param current 从 0 开始的页码。 / Zero-based page index. (optional)
     * @param size 单页记录数。 / Number of records per page. (optional, default to 30)
     * @param orderBy 排序字段列表。 / Ordered list of sort fields. (optional)
     * @param orderDirection 排序方向。 / Sort direction. (optional, default to OrderDirection.ASC)
     * @param orderNullHandling 空值排序策略。 / Null ordering strategy. (optional, default to OrderNullHandling.NATIVE)
     * @param keyword 标题或正文检索关键字。 / Title or content search keyword. (optional)
     * @param status 状态过滤条件。 / Status filter. (optional)
     * @return [ConversationSummaryList]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations")
    suspend fun listConversations(@Path("channelId") channelId: kotlin.String, @Query("current") current: kotlin.Int? = null, @Query("size") size: kotlin.Int? = 30, @Query("orderBy") orderBy: @JvmSuppressWildcards kotlin.collections.List<kotlin.String>? = null, @Query("orderDirection") orderDirection: OrderDirectionListConversations? = OrderDirectionListConversations.ASC, @Query("orderNullHandling") orderNullHandling: OrderNullHandlingListConversations? = OrderNullHandlingListConversations.NATIVE, @Query("keyword") keyword: kotlin.String? = null, @Query("status") status: kotlin.String? = null): Response<ConversationSummaryList>

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations/unread
     * 查询未读会话 / List unread conversations
     * 查询未读会话 / List unread conversations 请求会在身份验签和资源归属校验后执行；响应字段以本契约为准。 / The request runs after signature and resource-ownership validation; this contract defines the response fields.
     * Responses:
     *  - 200: 查询未读会话 / List unread conversations 的成功响应。 / Successful response for listUnreadConversations.
     *  - 401: API error
     *  - 403: API error
     *  - 413: API error
     *  - 422: Validation error
     *  - 429: API error
     *  - 500: API error
     *  - 503: API error
     *
     * @param channelId Agent OpenAPI 渠道 UUID。 / Agent OpenAPI channel UUID.
     * @return [ConversationIds]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/unread")
    suspend fun listUnreadConversations(@Path("channelId") channelId: kotlin.String): Response<ConversationIds>

    /**
     * PUT api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/read-receipt
     * 推进会话已读游标 / Mark a conversation as read
     * 推进会话已读游标 / Mark a conversation as read 请求会在身份验签和资源归属校验后执行；响应字段以本契约为准。 / The request runs after signature and resource-ownership validation; this contract defines the response fields.
     * Responses:
     *  - 200: 推进会话已读游标 / Mark a conversation as read 的成功响应。 / Successful response for markConversationRead.
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
     * @param conversationReadReceiptInput 推进会话已读游标 / Mark a conversation as read 的 JSON 请求参数。 / JSON request parameters for markConversationRead.
     * @return [ConversationReadReceipt]
     */
    @PUT("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/read-receipt")
    suspend fun markConversationRead(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Body conversationReadReceiptInput: ConversationReadReceiptInput): Response<ConversationReadReceipt>

    /**
     * POST api/agents/channel/openapi/v1/{channelId}/chat/conversations/activity/query
     * 批量查询会话活动 / Query conversation activities
     * 批量查询会话活动 / Query conversation activities 请求会在身份验签和资源归属校验后执行；响应字段以本契约为准。 / The request runs after signature and resource-ownership validation; this contract defines the response fields.
     * Responses:
     *  - 200: 批量查询会话活动 / Query conversation activities 的成功响应。 / Successful response for queryConversationActivities.
     *  - 401: API error
     *  - 403: API error
     *  - 413: API error
     *  - 422: Validation error
     *  - 429: API error
     *  - 500: API error
     *  - 503: API error
     *
     * @param channelId Agent OpenAPI 渠道 UUID。 / Agent OpenAPI channel UUID.
     * @param conversationActivityBatchInput 批量查询会话活动 / Query conversation activities 的 JSON 请求参数。 / JSON request parameters for queryConversationActivities.
     * @return [ConversationActivityList]
     */
    @POST("api/agents/channel/openapi/v1/{channelId}/chat/conversations/activity/query")
    suspend fun queryConversationActivities(@Path("channelId") channelId: kotlin.String, @Body conversationActivityBatchInput: ConversationActivityBatchInput): Response<ConversationActivityList>

    /**
     * DELETE api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/shares/{shareId}
     * 撤销会话分享 / Revoke a conversation share
     * 撤销会话分享 / Revoke a conversation share 请求会在身份验签和资源归属校验后执行；响应字段以本契约为准。 / The request runs after signature and resource-ownership validation; this contract defines the response fields.
     * Responses:
     *  - 200: 撤销会话分享 / Revoke a conversation share 的成功响应。 / Successful response for revokeConversationShare.
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
     * @param shareId 会话分享记录 ID。 / Conversation-share record ID.
     * @return [ConversationShareRevoked]
     */
    @DELETE("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/shares/{shareId}")
    suspend fun revokeConversationShare(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Path("shareId") shareId: kotlin.Long): Response<ConversationShareRevoked>

    /**
     * PATCH api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/status
     * 更新会话状态 / Update conversation status
     * 更新会话状态 / Update conversation status 请求会在身份验签和资源归属校验后执行；响应字段以本契约为准。 / The request runs after signature and resource-ownership validation; this contract defines the response fields.
     * Responses:
     *  - 202: 更新会话状态 / Update conversation status 的成功响应。 / Successful response for updateConversationStatus.
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
     * @param conversationStatusInput 更新会话状态 / Update conversation status 的 JSON 请求参数。 / JSON request parameters for updateConversationStatus.
     * @return [Unit]
     */
    @PATCH("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/status")
    suspend fun updateConversationStatus(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Body conversationStatusInput: ConversationStatusInput): Response<Unit>

    /**
     * PATCH api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/title
     * 更新会话标题 / Update conversation title
     * 更新会话标题 / Update conversation title 请求会在身份验签和资源归属校验后执行；响应字段以本契约为准。 / The request runs after signature and resource-ownership validation; this contract defines the response fields.
     * Responses:
     *  - 202: 更新会话标题 / Update conversation title 的成功响应。 / Successful response for updateConversationTitle.
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
     * @param conversationTitleInput 更新会话标题 / Update conversation title 的 JSON 请求参数。 / JSON request parameters for updateConversationTitle.
     * @return [Unit]
     */
    @PATCH("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/title")
    suspend fun updateConversationTitle(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Body conversationTitleInput: ConversationTitleInput): Response<Unit>

}

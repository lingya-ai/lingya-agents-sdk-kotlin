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
import ai.lingya.agents.sdk.generated.model.ConversationShare
import ai.lingya.agents.sdk.generated.model.ConversationShareInput
import ai.lingya.agents.sdk.generated.model.ConversationShareList
import ai.lingya.agents.sdk.generated.model.ConversationStats
import ai.lingya.agents.sdk.generated.model.ConversationStatusInput
import ai.lingya.agents.sdk.generated.model.ConversationSummaryList
import ai.lingya.agents.sdk.generated.model.ConversationTitle
import ai.lingya.agents.sdk.generated.model.ConversationTitleInput
import ai.lingya.agents.sdk.generated.model.ValidationError

interface ConversationsApi {
    /**
     * POST api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/shares
     * createConversationShare
     * 
     * Responses:
     *  - 201: Successful response
     *  - 401: API error
     *  - 403: API error
     *  - 413: API error
     *  - 422: Validation error
     *  - 429: API error
     *  - 500: API error
     *  - 503: API error
     *
     * @param channelId 
     * @param conversationId 
     * @param conversationShareInput 
     * @return [ConversationShare]
     */
    @POST("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/shares")
    suspend fun createConversationShare(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Body conversationShareInput: ConversationShareInput): Response<ConversationShare>

    /**
     * DELETE api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}
     * deleteConversation
     * 
     * Responses:
     *  - 200: Successful response
     *  - 401: API error
     *  - 403: API error
     *  - 413: API error
     *  - 422: Validation error
     *  - 429: API error
     *  - 500: API error
     *  - 503: API error
     *
     * @param channelId 
     * @param conversationId 
     * @return [Unit]
     */
    @DELETE("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}")
    suspend fun deleteConversation(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String): Response<Unit>

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/context-usage
     * getConversationContextUsage
     * 
     * Responses:
     *  - 200: Successful response
     *  - 401: API error
     *  - 403: API error
     *  - 413: API error
     *  - 422: Validation error
     *  - 429: API error
     *  - 500: API error
     *  - 503: API error
     *
     * @param channelId 
     * @param conversationId 
     * @return [ConversationContextUsage]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/context-usage")
    suspend fun getConversationContextUsage(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String): Response<ConversationContextUsage>

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations/stats
     * getConversationStats
     * 
     * Responses:
     *  - 200: Successful response
     *  - 401: API error
     *  - 403: API error
     *  - 413: API error
     *  - 422: Validation error
     *  - 429: API error
     *  - 500: API error
     *  - 503: API error
     *
     * @param channelId 
     * @return [ConversationStats]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/stats")
    suspend fun getConversationStats(@Path("channelId") channelId: kotlin.String): Response<ConversationStats>

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/title
     * getConversationTitle
     * 
     * Responses:
     *  - 200: Successful response
     *  - 401: API error
     *  - 403: API error
     *  - 413: API error
     *  - 422: Validation error
     *  - 429: API error
     *  - 500: API error
     *  - 503: API error
     *
     * @param channelId 
     * @param conversationId 
     * @return [ConversationTitle]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/title")
    suspend fun getConversationTitle(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String): Response<ConversationTitle>

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations/active
     * listActiveConversations
     * 
     * Responses:
     *  - 200: Successful response
     *  - 401: API error
     *  - 403: API error
     *  - 413: API error
     *  - 422: Validation error
     *  - 429: API error
     *  - 500: API error
     *  - 503: API error
     *
     * @param channelId 
     * @return [ConversationIds]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/active")
    suspend fun listActiveConversations(@Path("channelId") channelId: kotlin.String): Response<ConversationIds>

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/shares
     * listConversationShares
     * 
     * Responses:
     *  - 200: Successful response
     *  - 401: API error
     *  - 403: API error
     *  - 413: API error
     *  - 422: Validation error
     *  - 429: API error
     *  - 500: API error
     *  - 503: API error
     *
     * @param channelId 
     * @param conversationId 
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
     * listConversations
     * 
     * Responses:
     *  - 200: Successful response
     *  - 401: API error
     *  - 403: API error
     *  - 413: API error
     *  - 422: Validation error
     *  - 429: API error
     *  - 500: API error
     *  - 503: API error
     *
     * @param channelId 
     * @param current  (optional)
     * @param size  (optional, default to 30)
     * @param orderBy  (optional)
     * @param orderDirection  (optional, default to OrderDirection.ASC)
     * @param orderNullHandling  (optional, default to OrderNullHandling.NATIVE)
     * @param keyword  (optional)
     * @param status  (optional)
     * @return [ConversationSummaryList]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations")
    suspend fun listConversations(@Path("channelId") channelId: kotlin.String, @Query("current") current: kotlin.Int? = null, @Query("size") size: kotlin.Int? = 30, @Query("orderBy") orderBy: @JvmSuppressWildcards kotlin.collections.List<kotlin.String>? = null, @Query("orderDirection") orderDirection: OrderDirectionListConversations? = OrderDirectionListConversations.ASC, @Query("orderNullHandling") orderNullHandling: OrderNullHandlingListConversations? = OrderNullHandlingListConversations.NATIVE, @Query("keyword") keyword: kotlin.String? = null, @Query("status") status: kotlin.String? = null): Response<ConversationSummaryList>

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations/unread
     * listUnreadConversations
     * 
     * Responses:
     *  - 200: Successful response
     *  - 401: API error
     *  - 403: API error
     *  - 413: API error
     *  - 422: Validation error
     *  - 429: API error
     *  - 500: API error
     *  - 503: API error
     *
     * @param channelId 
     * @return [ConversationIds]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/unread")
    suspend fun listUnreadConversations(@Path("channelId") channelId: kotlin.String): Response<ConversationIds>

    /**
     * PUT api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/read-receipt
     * markConversationRead
     * 
     * Responses:
     *  - 200: Successful response
     *  - 401: API error
     *  - 403: API error
     *  - 413: API error
     *  - 422: Validation error
     *  - 429: API error
     *  - 500: API error
     *  - 503: API error
     *
     * @param channelId 
     * @param conversationId 
     * @param conversationReadReceiptInput 
     * @return [ConversationReadReceipt]
     */
    @PUT("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/read-receipt")
    suspend fun markConversationRead(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Body conversationReadReceiptInput: ConversationReadReceiptInput): Response<ConversationReadReceipt>

    /**
     * POST api/agents/channel/openapi/v1/{channelId}/chat/conversations/activity/query
     * queryConversationActivities
     * 
     * Responses:
     *  - 200: Successful response
     *  - 401: API error
     *  - 403: API error
     *  - 413: API error
     *  - 422: Validation error
     *  - 429: API error
     *  - 500: API error
     *  - 503: API error
     *
     * @param channelId 
     * @param conversationActivityBatchInput 
     * @return [ConversationActivityList]
     */
    @POST("api/agents/channel/openapi/v1/{channelId}/chat/conversations/activity/query")
    suspend fun queryConversationActivities(@Path("channelId") channelId: kotlin.String, @Body conversationActivityBatchInput: ConversationActivityBatchInput): Response<ConversationActivityList>

    /**
     * DELETE api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/shares/{shareId}
     * revokeConversationShare
     * 
     * Responses:
     *  - 200: Successful response
     *  - 401: API error
     *  - 403: API error
     *  - 413: API error
     *  - 422: Validation error
     *  - 429: API error
     *  - 500: API error
     *  - 503: API error
     *
     * @param channelId 
     * @param conversationId 
     * @param shareId 
     * @return [ConversationShare]
     */
    @DELETE("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/shares/{shareId}")
    suspend fun revokeConversationShare(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Path("shareId") shareId: kotlin.Long): Response<ConversationShare>

    /**
     * PATCH api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/status
     * updateConversationStatus
     * 
     * Responses:
     *  - 202: Successful response
     *  - 401: API error
     *  - 403: API error
     *  - 413: API error
     *  - 422: Validation error
     *  - 429: API error
     *  - 500: API error
     *  - 503: API error
     *
     * @param channelId 
     * @param conversationId 
     * @param conversationStatusInput 
     * @return [Unit]
     */
    @PATCH("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/status")
    suspend fun updateConversationStatus(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Body conversationStatusInput: ConversationStatusInput): Response<Unit>

    /**
     * PATCH api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/title
     * updateConversationTitle
     * 
     * Responses:
     *  - 202: Successful response
     *  - 401: API error
     *  - 403: API error
     *  - 413: API error
     *  - 422: Validation error
     *  - 429: API error
     *  - 500: API error
     *  - 503: API error
     *
     * @param channelId 
     * @param conversationId 
     * @param conversationTitleInput 
     * @return [Unit]
     */
    @PATCH("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/title")
    suspend fun updateConversationTitle(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Body conversationTitleInput: ConversationTitleInput): Response<Unit>

}

package ai.lingya.agents.sdk.generated.api

import ai.lingya.agents.sdk.generated.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.fasterxml.jackson.annotation.JsonProperty

import ai.lingya.agents.sdk.generated.model.AsyncTask
import ai.lingya.agents.sdk.generated.model.AsyncTaskPage
import ai.lingya.agents.sdk.generated.model.CodeMessage
import ai.lingya.agents.sdk.generated.model.ConversationMessage
import ai.lingya.agents.sdk.generated.model.ConversationMessagePage
import ai.lingya.agents.sdk.generated.model.ValidationError

interface MessagesApi {
    /**
     * DELETE api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/messages/{messageId}/queue
     * cancelQueuedMessage
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
     * @param messageId 
     * @return [ConversationMessage]
     */
    @DELETE("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/messages/{messageId}/queue")
    suspend fun cancelQueuedMessage(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Path("messageId") messageId: kotlin.String): Response<ConversationMessage>

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/async-tasks/{asyncTaskId}
     * getConversationAsyncTask
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
     * @param asyncTaskId 
     * @return [AsyncTask]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/async-tasks/{asyncTaskId}")
    suspend fun getConversationAsyncTask(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Path("asyncTaskId") asyncTaskId: kotlin.String): Response<AsyncTask>

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/messages/{messageId}
     * getConversationMessage
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
     * @param messageId 
     * @return [ConversationMessage]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/messages/{messageId}")
    suspend fun getConversationMessage(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Path("messageId") messageId: kotlin.String): Response<ConversationMessage>


    /**
    * enum for parameter orderDirection
    */
    enum class OrderDirectionListConversationAsyncTasks(val value: kotlin.String) {
            @JsonProperty(value = "ASC") ASC("ASC"),
            @JsonProperty(value = "DESC") DESC("DESC"),
    }


    /**
    * enum for parameter orderNullHandling
    */
    enum class OrderNullHandlingListConversationAsyncTasks(val value: kotlin.String) {
            @JsonProperty(value = "NATIVE") NATIVE("NATIVE"),
            @JsonProperty(value = "NULLS_FIRST") NULLS_FIRST("NULLS_FIRST"),
            @JsonProperty(value = "NULLS_LAST") NULLS_LAST("NULLS_LAST"),
    }

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/async-tasks
     * listConversationAsyncTasks
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
     * @param current  (optional)
     * @param size  (optional, default to 30)
     * @param orderBy  (optional)
     * @param orderDirection  (optional, default to OrderDirection.ASC)
     * @param orderNullHandling  (optional, default to OrderNullHandling.NATIVE)
     * @param keyword  (optional)
     * @param status  (optional)
     * @return [AsyncTaskPage]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/async-tasks")
    suspend fun listConversationAsyncTasks(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Query("current") current: kotlin.Int? = null, @Query("size") size: kotlin.Int? = 30, @Query("orderBy") orderBy: @JvmSuppressWildcards kotlin.collections.List<kotlin.String>? = null, @Query("orderDirection") orderDirection: OrderDirectionListConversationAsyncTasks? = OrderDirectionListConversationAsyncTasks.ASC, @Query("orderNullHandling") orderNullHandling: OrderNullHandlingListConversationAsyncTasks? = OrderNullHandlingListConversationAsyncTasks.NATIVE, @Query("keyword") keyword: kotlin.String? = null, @Query("status") status: @JvmSuppressWildcards kotlin.collections.List<kotlin.String>? = null): Response<AsyncTaskPage>


    /**
    * enum for parameter orderDirection
    */
    enum class OrderDirectionListConversationMessages(val value: kotlin.String) {
            @JsonProperty(value = "ASC") ASC("ASC"),
            @JsonProperty(value = "DESC") DESC("DESC"),
    }


    /**
    * enum for parameter orderNullHandling
    */
    enum class OrderNullHandlingListConversationMessages(val value: kotlin.String) {
            @JsonProperty(value = "NATIVE") NATIVE("NATIVE"),
            @JsonProperty(value = "NULLS_FIRST") NULLS_FIRST("NULLS_FIRST"),
            @JsonProperty(value = "NULLS_LAST") NULLS_LAST("NULLS_LAST"),
    }

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/messages
     * listConversationMessages
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
     * @param current  (optional)
     * @param size  (optional, default to 30)
     * @param orderBy  (optional)
     * @param orderDirection  (optional, default to OrderDirection.ASC)
     * @param orderNullHandling  (optional, default to OrderNullHandling.NATIVE)
     * @param keyword  (optional)
     * @return [ConversationMessagePage]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/messages")
    suspend fun listConversationMessages(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Query("current") current: kotlin.Int? = null, @Query("size") size: kotlin.Int? = 30, @Query("orderBy") orderBy: @JvmSuppressWildcards kotlin.collections.List<kotlin.String>? = null, @Query("orderDirection") orderDirection: OrderDirectionListConversationMessages? = OrderDirectionListConversationMessages.ASC, @Query("orderNullHandling") orderNullHandling: OrderNullHandlingListConversationMessages? = OrderNullHandlingListConversationMessages.NATIVE, @Query("keyword") keyword: kotlin.String? = null): Response<ConversationMessagePage>

}

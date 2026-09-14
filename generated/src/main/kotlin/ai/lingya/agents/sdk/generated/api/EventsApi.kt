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
     * getChatEvents
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
     * @return [AiChatBriefEventList]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/events")
    suspend fun getChatEvents(@Path("channelId") channelId: kotlin.String, @Query("conversationId") conversationId: kotlin.String, @Query("messageId") messageId: kotlin.String): Response<AiChatBriefEventList>

    /**
     * POST api/agents/channel/openapi/v1/{channelId}/chat/events/batch
     * getChatEventsBatch
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
     * @param aiChatEventsBatchInput 
     * @return [AiChatEventsBatch]
     */
    @POST("api/agents/channel/openapi/v1/{channelId}/chat/events/batch")
    suspend fun getChatEventsBatch(@Path("channelId") channelId: kotlin.String, @Body aiChatEventsBatchInput: AiChatEventsBatchInput): Response<AiChatEventsBatch>

}

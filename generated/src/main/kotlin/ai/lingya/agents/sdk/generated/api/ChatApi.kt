package ai.lingya.agents.sdk.generated.api

import ai.lingya.agents.sdk.generated.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.fasterxml.jackson.annotation.JsonProperty

import ai.lingya.agents.sdk.generated.model.AiChatBriefEvent
import ai.lingya.agents.sdk.generated.model.AiChatInput
import ai.lingya.agents.sdk.generated.model.AiChatStreamInput
import ai.lingya.agents.sdk.generated.model.AiChatSubmission
import ai.lingya.agents.sdk.generated.model.ChatStreamProbeEvent
import ai.lingya.agents.sdk.generated.model.ChatStreamProbeInput
import ai.lingya.agents.sdk.generated.model.CodeMessage
import ai.lingya.agents.sdk.generated.model.ValidationError

interface ChatApi {
    /**
     * POST api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/compact
     * compactConversation
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
     * @param force  (optional, default to true)
     * @return [Unit]
     */
    @POST("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/compact")
    suspend fun compactConversation(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Query("force") force: kotlin.Boolean? = true): Response<Unit>

    /**
     * POST api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}
     * continueChat
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
     * @param aiChatInput 
     * @return [AiChatSubmission]
     */
    @POST("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}")
    suspend fun continueChat(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Body aiChatInput: AiChatInput): Response<AiChatSubmission>

    /**
     * POST api/agents/channel/openapi/v1/{channelId}/chat
     * createChat
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
     * @param aiChatInput 
     * @return [AiChatSubmission]
     */
    @POST("api/agents/channel/openapi/v1/{channelId}/chat")
    suspend fun createChat(@Path("channelId") channelId: kotlin.String, @Body aiChatInput: AiChatInput): Response<AiChatSubmission>

    /**
     * DELETE api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/interrupt
     * interruptConversation
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
    @DELETE("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/interrupt")
    suspend fun interruptConversation(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String): Response<Unit>

}

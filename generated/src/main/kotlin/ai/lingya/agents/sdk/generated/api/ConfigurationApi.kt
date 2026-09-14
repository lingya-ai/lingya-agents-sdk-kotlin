package ai.lingya.agents.sdk.generated.api

import ai.lingya.agents.sdk.generated.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.fasterxml.jackson.annotation.JsonProperty

import ai.lingya.agents.sdk.generated.model.AgentsConfig
import ai.lingya.agents.sdk.generated.model.CodeMessage
import ai.lingya.agents.sdk.generated.model.ConversationConfig
import ai.lingya.agents.sdk.generated.model.ValidationError

interface ConfigurationApi {
    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/config
     * getAgentsConfig
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
     * @return [AgentsConfig]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/config")
    suspend fun getAgentsConfig(@Path("channelId") channelId: kotlin.String): Response<AgentsConfig>

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/config
     * getConversationConfig
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
     * @return [ConversationConfig]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/config")
    suspend fun getConversationConfig(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String): Response<ConversationConfig>

}

package ai.lingya.agents.sdk.generated.api

import ai.lingya.agents.sdk.generated.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.fasterxml.jackson.annotation.JsonProperty

import ai.lingya.agents.sdk.generated.model.CitationMetadata
import ai.lingya.agents.sdk.generated.model.CitationMetadataList
import ai.lingya.agents.sdk.generated.model.CodeMessage
import ai.lingya.agents.sdk.generated.model.ReturnedReference
import ai.lingya.agents.sdk.generated.model.ValidationError

interface KnowledgeApi {
    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/knowledge-bases/citations/{citationType}/{referenceId}/metadata
     * getCitationMetadata
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
     * @param citationType 
     * @param referenceId 
     * @return [CitationMetadata]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/knowledge-bases/citations/{citationType}/{referenceId}/metadata")
    suspend fun getCitationMetadata(@Path("channelId") channelId: kotlin.String, @Path("citationType") citationType: kotlin.String, @Path("referenceId") referenceId: kotlin.Long): Response<CitationMetadata>

    /**
     * POST api/agents/channel/openapi/v1/{channelId}/chat/knowledge-bases/citations/metadata
     * getCitationMetadataBatch
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
     * @param returnedReference 
     * @return [CitationMetadataList]
     */
    @POST("api/agents/channel/openapi/v1/{channelId}/chat/knowledge-bases/citations/metadata")
    suspend fun getCitationMetadataBatch(@Path("channelId") channelId: kotlin.String, @Body returnedReference: kotlin.collections.List<ReturnedReference>): Response<CitationMetadataList>

}

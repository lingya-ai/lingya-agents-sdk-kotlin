package ai.lingya.agents.sdk.generated.api

import ai.lingya.agents.sdk.generated.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.fasterxml.jackson.annotation.JsonProperty

import ai.lingya.agents.sdk.generated.model.CodeMessage
import ai.lingya.agents.sdk.generated.model.PreSignedReadUrl
import ai.lingya.agents.sdk.generated.model.ValidationError
import ai.lingya.agents.sdk.generated.model.WorkspaceArtifactList

interface WorkspaceApi {
    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/workspace/files/preview
     * getWorkspaceFilePreview
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
     * @param path 
     * @return [PreSignedReadUrl]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/workspace/files/preview")
    suspend fun getWorkspaceFilePreview(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Query("path") path: kotlin.String): Response<PreSignedReadUrl>


    /**
    * enum for parameter orderDirection
    */
    enum class OrderDirectionListWorkspaceArtifacts(val value: kotlin.String) {
            @JsonProperty(value = "ASC") ASC("ASC"),
            @JsonProperty(value = "DESC") DESC("DESC"),
    }


    /**
    * enum for parameter orderNullHandling
    */
    enum class OrderNullHandlingListWorkspaceArtifacts(val value: kotlin.String) {
            @JsonProperty(value = "NATIVE") NATIVE("NATIVE"),
            @JsonProperty(value = "NULLS_FIRST") NULLS_FIRST("NULLS_FIRST"),
            @JsonProperty(value = "NULLS_LAST") NULLS_LAST("NULLS_LAST"),
    }

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/workspace/files
     * listWorkspaceArtifacts
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
     * @param prefix  (optional)
     * @return [WorkspaceArtifactList]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/workspace/files")
    suspend fun listWorkspaceArtifacts(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Query("current") current: kotlin.Int? = null, @Query("size") size: kotlin.Int? = 30, @Query("orderBy") orderBy: @JvmSuppressWildcards kotlin.collections.List<kotlin.String>? = null, @Query("orderDirection") orderDirection: OrderDirectionListWorkspaceArtifacts? = OrderDirectionListWorkspaceArtifacts.ASC, @Query("orderNullHandling") orderNullHandling: OrderNullHandlingListWorkspaceArtifacts? = OrderNullHandlingListWorkspaceArtifacts.NATIVE, @Query("keyword") keyword: kotlin.String? = null, @Query("prefix") prefix: kotlin.String? = null): Response<WorkspaceArtifactList>

}

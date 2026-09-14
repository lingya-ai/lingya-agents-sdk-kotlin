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
     * 读取引用元数据 / Get citation metadata
     * 读取引用元数据 / Get citation metadata 请求会在身份验签和资源归属校验后执行；响应字段以本契约为准。 / The request runs after signature and resource-ownership validation; this contract defines the response fields.
     * Responses:
     *  - 200: 读取引用元数据 / Get citation metadata 的成功响应。 / Successful response for getCitationMetadata.
     *  - 401: API error
     *  - 403: API error
     *  - 413: API error
     *  - 422: Validation error
     *  - 429: API error
     *  - 500: API error
     *  - 503: API error
     *
     * @param channelId Agent OpenAPI 渠道 UUID。 / Agent OpenAPI channel UUID.
     * @param citationType 知识引用类型。 / Knowledge citation type.
     * @param referenceId 知识引用记录 ID。 / Knowledge-reference record ID.
     * @return [CitationMetadata]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/knowledge-bases/citations/{citationType}/{referenceId}/metadata")
    suspend fun getCitationMetadata(@Path("channelId") channelId: kotlin.String, @Path("citationType") citationType: kotlin.String, @Path("referenceId") referenceId: kotlin.Long): Response<CitationMetadata>

    /**
     * POST api/agents/channel/openapi/v1/{channelId}/chat/knowledge-bases/citations/metadata
     * 批量读取引用元数据 / Get citation metadata in batch
     * 批量读取引用元数据 / Get citation metadata in batch 请求会在身份验签和资源归属校验后执行；响应字段以本契约为准。 / The request runs after signature and resource-ownership validation; this contract defines the response fields.
     * Responses:
     *  - 201: 批量读取引用元数据 / Get citation metadata in batch 的成功响应。 / Successful response for getCitationMetadataBatch.
     *  - 401: API error
     *  - 403: API error
     *  - 413: API error
     *  - 422: Validation error
     *  - 429: API error
     *  - 500: API error
     *  - 503: API error
     *
     * @param channelId Agent OpenAPI 渠道 UUID。 / Agent OpenAPI channel UUID.
     * @param returnedReference 批量读取引用元数据 / Get citation metadata in batch 的 JSON 请求参数。 / JSON request parameters for getCitationMetadataBatch.
     * @return [CitationMetadataList]
     */
    @POST("api/agents/channel/openapi/v1/{channelId}/chat/knowledge-bases/citations/metadata")
    suspend fun getCitationMetadataBatch(@Path("channelId") channelId: kotlin.String, @Body returnedReference: kotlin.collections.List<ReturnedReference>): Response<CitationMetadataList>

}

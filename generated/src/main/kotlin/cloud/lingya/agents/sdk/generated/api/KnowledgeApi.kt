package cloud.lingya.agents.sdk.generated.api

import cloud.lingya.agents.sdk.generated.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.fasterxml.jackson.annotation.JsonProperty

import cloud.lingya.agents.sdk.generated.model.CitationMetadata
import cloud.lingya.agents.sdk.generated.model.CitationMetadataList
import cloud.lingya.agents.sdk.generated.model.CodeMessage
import cloud.lingya.agents.sdk.generated.model.ReturnedReference
import cloud.lingya.agents.sdk.generated.model.ValidationError

interface KnowledgeApi {
    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/knowledge-bases/citations/{citationType}/{referenceId}/metadata
     * 读取引用元数据 / Get citation metadata
     * ### 使用场景 用户展开单条引用时读取来源详情。  ### Use case Read source detail when a user expands one citation.  ### 前置条件 citationType 与 referenceId 来自服务端。  ### Prerequisites citationType and referenceId come from the server.  ### 行为与副作用 只读。  ### Behavior and side effects Read-only.  ### 后续调用 展示标题、位置和其他元数据。  ### Next step Display title, location, and other metadata.  ### 接口摘要 读取引用元数据 / Get citation metadata  ### Operation summary 读取引用元数据 / Get citation metadata
     * Responses:
     *  - 200: 读取引用元数据 / Get citation metadata 的成功响应。 / Successful response for getCitationMetadata.
     *  - 401: 接口错误。 / API error.
     *  - 403: 接口错误。 / API error.
     *  - 413: 接口错误。 / API error.
     *  - 422: 参数校验错误。 / Validation error.
     *  - 429: 接口错误。 / API error.
     *  - 500: 接口错误。 / API error.
     *  - 503: 接口错误。 / API error.
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
     * ### 使用场景 一次解析回答中的多条知识引用。  ### Use case Resolve multiple knowledge citations from an answer in one request.  ### 前置条件 引用列表来自服务端消息或事件。  ### Prerequisites The reference list comes from a server message or event.  ### 行为与副作用 只读，返回可展示元数据。  ### Behavior and side effects Read-only and returns display metadata.  ### 后续调用 按原引用顺序渲染来源。  ### Next step Render sources in the original reference order.  ### 接口摘要 批量读取引用元数据 / Get citation metadata in batch  ### Operation summary 批量读取引用元数据 / Get citation metadata in batch
     * Responses:
     *  - 201: 批量读取引用元数据 / Get citation metadata in batch 的成功响应。 / Successful response for getCitationMetadataBatch.
     *  - 401: 接口错误。 / API error.
     *  - 403: 接口错误。 / API error.
     *  - 413: 接口错误。 / API error.
     *  - 422: 参数校验错误。 / Validation error.
     *  - 429: 接口错误。 / API error.
     *  - 500: 接口错误。 / API error.
     *  - 503: 接口错误。 / API error.
     *
     * @param channelId Agent OpenAPI 渠道 UUID。 / Agent OpenAPI channel UUID.
     * @param returnedReference 批量读取引用元数据 / Get citation metadata in batch 的 JSON 请求参数。 / JSON request parameters for getCitationMetadataBatch.
     * @return [CitationMetadataList]
     */
    @POST("api/agents/channel/openapi/v1/{channelId}/chat/knowledge-bases/citations/metadata")
    suspend fun getCitationMetadataBatch(@Path("channelId") channelId: kotlin.String, @Body returnedReference: kotlin.collections.List<ReturnedReference>): Response<CitationMetadataList>

}

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
     * 创建工作区文件预览地址 / Create a workspace file preview URL
     * 创建工作区文件预览地址 / Create a workspace file preview URL 请求会在身份验签和资源归属校验后执行；响应字段以本契约为准。 / The request runs after signature and resource-ownership validation; this contract defines the response fields.
     * Responses:
     *  - 200: 创建工作区文件预览地址 / Create a workspace file preview URL 的成功响应。 / Successful response for getWorkspaceFilePreview.
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
     * @param path 工作区相对文件路径。 / Workspace-relative file path.
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
     * 分页查询工作区制品 / List workspace artifacts
     * 分页查询工作区制品 / List workspace artifacts 请求会在身份验签和资源归属校验后执行；响应字段以本契约为准。 / The request runs after signature and resource-ownership validation; this contract defines the response fields.
     * Responses:
     *  - 200: 分页查询工作区制品 / List workspace artifacts 的成功响应。 / Successful response for listWorkspaceArtifacts.
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
     * @param current 从 0 开始的页码。 / Zero-based page index. (optional)
     * @param size 单页记录数。 / Number of records per page. (optional, default to 30)
     * @param orderBy 排序字段列表。 / Ordered list of sort fields. (optional)
     * @param orderDirection 排序方向。 / Sort direction. (optional, default to OrderDirection.ASC)
     * @param orderNullHandling 空值排序策略。 / Null ordering strategy. (optional, default to OrderNullHandling.NATIVE)
     * @param keyword 标题或正文检索关键字。 / Title or content search keyword. (optional)
     * @param prefix 工作区相对路径前缀。 / Workspace-relative path prefix. (optional)
     * @return [WorkspaceArtifactList]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/workspace/files")
    suspend fun listWorkspaceArtifacts(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Query("current") current: kotlin.Int? = null, @Query("size") size: kotlin.Int? = 30, @Query("orderBy") orderBy: @JvmSuppressWildcards kotlin.collections.List<kotlin.String>? = null, @Query("orderDirection") orderDirection: OrderDirectionListWorkspaceArtifacts? = OrderDirectionListWorkspaceArtifacts.ASC, @Query("orderNullHandling") orderNullHandling: OrderNullHandlingListWorkspaceArtifacts? = OrderNullHandlingListWorkspaceArtifacts.NATIVE, @Query("keyword") keyword: kotlin.String? = null, @Query("prefix") prefix: kotlin.String? = null): Response<WorkspaceArtifactList>

}

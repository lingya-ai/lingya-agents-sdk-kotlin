package cloud.lingya.agents.sdk.generated.api

import cloud.lingya.agents.sdk.generated.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import okhttp3.ResponseBody
import com.fasterxml.jackson.annotation.JsonProperty

import cloud.lingya.agents.sdk.generated.model.CodeMessage
import cloud.lingya.agents.sdk.generated.model.SqlChartDataset
import cloud.lingya.agents.sdk.generated.model.SqlQueryResultPage
import cloud.lingya.agents.sdk.generated.model.ValidationError

interface SQLApi {

    /**
    * enum for parameter format
    */
    enum class FormatExportSqlQueryResult(val value: kotlin.String) {
            @JsonProperty(value = "CSV") CSV("CSV"),
            @JsonProperty(value = "XLSX") XLSX("XLSX"),
    }

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/sql-query-results/{resultId}/export
     * 导出 SQL 结果 / Export SQL results
     * 导出 SQL 结果 / Export SQL results 请求会在身份验签和资源归属校验后执行；响应字段以本契约为准。 / The request runs after signature and resource-ownership validation; this contract defines the response fields.
     * Responses:
     *  - 200: Streaming export
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
     * @param resultId SQL 查询结果 ID。 / SQL query-result ID.
     * @param format 导出格式。 / Export format.
     * @param accept 期望的导出媒体类型。 / Requested export media type. (optional)
     * @return [ResponseBody]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/sql-query-results/{resultId}/export")
    suspend fun exportSqlQueryResult(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Path("resultId") resultId: kotlin.String, @Query("format") format: FormatExportSqlQueryResult, @Header("Accept") accept: kotlin.String? = null): Response<ResponseBody>

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/sql-query-results/{resultId}/chart-data
     * 读取 SQL 图表数据 / Get SQL chart data
     * 读取 SQL 图表数据 / Get SQL chart data 请求会在身份验签和资源归属校验后执行；响应字段以本契约为准。 / The request runs after signature and resource-ownership validation; this contract defines the response fields.
     * Responses:
     *  - 200: 读取 SQL 图表数据 / Get SQL chart data 的成功响应。 / Successful response for getSqlQueryChartData.
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
     * @param resultId SQL 查询结果 ID。 / SQL query-result ID.
     * @return [SqlChartDataset]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/sql-query-results/{resultId}/chart-data")
    suspend fun getSqlQueryChartData(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Path("resultId") resultId: kotlin.String): Response<SqlChartDataset>

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/sql-query-results/{resultId}
     * 分页读取 SQL 结果 / Get paged SQL results
     * 分页读取 SQL 结果 / Get paged SQL results 请求会在身份验签和资源归属校验后执行；响应字段以本契约为准。 / The request runs after signature and resource-ownership validation; this contract defines the response fields.
     * Responses:
     *  - 200: 分页读取 SQL 结果 / Get paged SQL results 的成功响应。 / Successful response for getSqlQueryResult.
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
     * @param resultId SQL 查询结果 ID。 / SQL query-result ID.
     * @param current 从 0 开始的页码。 / Zero-based page index. (optional, default to 0)
     * @param size 单页记录数。 / Number of records per page. (optional, default to 100)
     * @return [SqlQueryResultPage]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/sql-query-results/{resultId}")
    suspend fun getSqlQueryResult(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Path("resultId") resultId: kotlin.String, @Query("current") current: kotlin.Int? = 0, @Query("size") size: kotlin.Int? = 100): Response<SqlQueryResultPage>

}

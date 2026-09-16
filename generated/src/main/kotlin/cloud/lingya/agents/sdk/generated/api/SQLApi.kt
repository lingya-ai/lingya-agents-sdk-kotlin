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
     * ### 使用场景 把完整 SQL 结果下载为 CSV 或 XLSX。  ### Use case Download the complete SQL result as CSV or XLSX.  ### 前置条件 resultId 有效，format 与 Accept 匹配。  ### Prerequisites The resultId is valid and format matches Accept.  ### 行为与副作用 返回二进制下载流。  ### Behavior and side effects Returns a binary download stream.  ### 后续调用 按 Content-Disposition 保存文件，不要解析为 JSON。  ### Next step Save using Content-Disposition and do not parse as JSON.  ### 接口摘要 导出 SQL 结果 / Export SQL results  ### Operation summary 导出 SQL 结果 / Export SQL results
     * Responses:
     *  - 200: Streaming export
     *  - 401: 接口错误。 / API error.
     *  - 403: 接口错误。 / API error.
     *  - 413: 接口错误。 / API error.
     *  - 422: 参数校验错误。 / Validation error.
     *  - 429: 接口错误。 / API error.
     *  - 500: 接口错误。 / API error.
     *  - 503: 接口错误。 / API error.
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
     * ### 使用场景 取得适合前端图表渲染的 SQL 数据集。  ### Use case Obtain a SQL dataset prepared for chart rendering.  ### 前置条件 resultId 对应可图表化的 SQL 结果。  ### Prerequisites The resultId identifies a chartable SQL result.  ### 行为与副作用 只读。  ### Behavior and side effects Read-only.  ### 后续调用 按返回的列类型构建图表。  ### Next step Build charts using the returned column types.  ### 接口摘要 读取 SQL 图表数据 / Get SQL chart data  ### Operation summary 读取 SQL 图表数据 / Get SQL chart data
     * Responses:
     *  - 200: 读取 SQL 图表数据 / Get SQL chart data 的成功响应。 / Successful response for getSqlQueryChartData.
     *  - 401: 接口错误。 / API error.
     *  - 403: 接口错误。 / API error.
     *  - 413: 接口错误。 / API error.
     *  - 422: 参数校验错误。 / Validation error.
     *  - 429: 接口错误。 / API error.
     *  - 500: 接口错误。 / API error.
     *  - 503: 接口错误。 / API error.
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
     * ### 使用场景 分页查看 SQL 工具返回的表格数据。  ### Use case Inspect tabular data returned by the SQL tool page by page.  ### 前置条件 从事件或消息中取得 conversationId 和 resultId。  ### Prerequisites Obtain conversationId and resultId from an event or message.  ### 行为与副作用 只读，返回当前页和列定义。  ### Behavior and side effects Read-only and returns a page plus column definitions.  ### 后续调用 按需读取图表数据或导出。  ### Next step Read chart data or export as needed.  ### 接口摘要 分页读取 SQL 结果 / Get paged SQL results  ### Operation summary 分页读取 SQL 结果 / Get paged SQL results
     * Responses:
     *  - 200: 分页读取 SQL 结果 / Get paged SQL results 的成功响应。 / Successful response for getSqlQueryResult.
     *  - 401: 接口错误。 / API error.
     *  - 403: 接口错误。 / API error.
     *  - 413: 接口错误。 / API error.
     *  - 422: 参数校验错误。 / Validation error.
     *  - 429: 接口错误。 / API error.
     *  - 500: 接口错误。 / API error.
     *  - 503: 接口错误。 / API error.
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

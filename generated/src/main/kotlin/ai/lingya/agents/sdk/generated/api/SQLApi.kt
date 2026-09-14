package ai.lingya.agents.sdk.generated.api

import ai.lingya.agents.sdk.generated.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import okhttp3.ResponseBody
import com.fasterxml.jackson.annotation.JsonProperty

import ai.lingya.agents.sdk.generated.model.CodeMessage
import ai.lingya.agents.sdk.generated.model.SqlQueryResultPage
import ai.lingya.agents.sdk.generated.model.ValidationError

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
     * exportSqlQueryResult
     * 
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
     * @param channelId 
     * @param conversationId 
     * @param resultId 
     * @param format 
     * @param accept Requested export media type. (optional)
     * @return [ResponseBody]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/sql-query-results/{resultId}/export")
    suspend fun exportSqlQueryResult(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Path("resultId") resultId: kotlin.String, @Query("format") format: FormatExportSqlQueryResult, @Header("Accept") accept: kotlin.String? = null): Response<ResponseBody>

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/sql-query-results/{resultId}/chart-data
     * getSqlQueryChartData
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
     * @param resultId 
     * @return [kotlin.collections.Map<kotlin.String, kotlin.Any>]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/sql-query-results/{resultId}/chart-data")
    suspend fun getSqlQueryChartData(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Path("resultId") resultId: kotlin.String): Response<kotlin.collections.Map<kotlin.String, kotlin.Any>>

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/sql-query-results/{resultId}
     * getSqlQueryResult
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
     * @param resultId 
     * @param current  (optional, default to 0)
     * @param size  (optional, default to 100)
     * @return [SqlQueryResultPage]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/sql-query-results/{resultId}")
    suspend fun getSqlQueryResult(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Path("resultId") resultId: kotlin.String, @Query("current") current: kotlin.Int? = 0, @Query("size") size: kotlin.Int? = 100): Response<SqlQueryResultPage>

}

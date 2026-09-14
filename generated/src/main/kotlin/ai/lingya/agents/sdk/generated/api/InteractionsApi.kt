package ai.lingya.agents.sdk.generated.api

import ai.lingya.agents.sdk.generated.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.fasterxml.jackson.annotation.JsonProperty

import ai.lingya.agents.sdk.generated.model.CodeMessage
import ai.lingya.agents.sdk.generated.model.OperationResult
import ai.lingya.agents.sdk.generated.model.PlanApprovalInput
import ai.lingya.agents.sdk.generated.model.PlanStatus
import ai.lingya.agents.sdk.generated.model.UserInputAnswerInput
import ai.lingya.agents.sdk.generated.model.UserInputStatus
import ai.lingya.agents.sdk.generated.model.ValidationError

interface InteractionsApi {
    /**
     * POST api/agents/channel/openapi/v1/{channelId}/chat/user-input/answer
     * answerUserInput
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
     * @param userInputAnswerInput 
     * @return [OperationResult]
     */
    @POST("api/agents/channel/openapi/v1/{channelId}/chat/user-input/answer")
    suspend fun answerUserInput(@Path("channelId") channelId: kotlin.String, @Body userInputAnswerInput: UserInputAnswerInput): Response<OperationResult>

    /**
     * POST api/agents/channel/openapi/v1/{channelId}/chat/plan/approve
     * approvePlan
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
     * @param planApprovalInput 
     * @return [OperationResult]
     */
    @POST("api/agents/channel/openapi/v1/{channelId}/chat/plan/approve")
    suspend fun approvePlan(@Path("channelId") channelId: kotlin.String, @Body planApprovalInput: PlanApprovalInput): Response<OperationResult>

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/plan/{planId}/status
     * getPlanStatus
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
     * @param planId 
     * @return [PlanStatus]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/plan/{planId}/status")
    suspend fun getPlanStatus(@Path("channelId") channelId: kotlin.String, @Path("planId") planId: kotlin.String): Response<PlanStatus>

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/user-input/{questionId}/status
     * getUserInputStatus
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
     * @param questionId 
     * @param conversationId 
     * @param messageId 
     * @return [UserInputStatus]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/user-input/{questionId}/status")
    suspend fun getUserInputStatus(@Path("channelId") channelId: kotlin.String, @Path("questionId") questionId: kotlin.String, @Query("conversationId") conversationId: kotlin.String, @Query("messageId") messageId: kotlin.String): Response<UserInputStatus>

}

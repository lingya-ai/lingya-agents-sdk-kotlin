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
     * 提交用户回答 / Submit a user answer
     * 提交用户回答 / Submit a user answer 请求会在身份验签和资源归属校验后执行；响应字段以本契约为准。 / The request runs after signature and resource-ownership validation; this contract defines the response fields.
     * Responses:
     *  - 201: 提交用户回答 / Submit a user answer 的成功响应。 / Successful response for answerUserInput.
     *  - 401: API error
     *  - 403: API error
     *  - 413: API error
     *  - 422: Validation error
     *  - 429: API error
     *  - 500: API error
     *  - 503: API error
     *
     * @param channelId Agent OpenAPI 渠道 UUID。 / Agent OpenAPI channel UUID.
     * @param userInputAnswerInput 提交用户回答 / Submit a user answer 的 JSON 请求参数。 / JSON request parameters for answerUserInput.
     * @return [OperationResult]
     */
    @POST("api/agents/channel/openapi/v1/{channelId}/chat/user-input/answer")
    suspend fun answerUserInput(@Path("channelId") channelId: kotlin.String, @Body userInputAnswerInput: UserInputAnswerInput): Response<OperationResult>

    /**
     * POST api/agents/channel/openapi/v1/{channelId}/chat/plan/approve
     * 提交计划审批 / Submit plan approval
     * 提交计划审批 / Submit plan approval 请求会在身份验签和资源归属校验后执行；响应字段以本契约为准。 / The request runs after signature and resource-ownership validation; this contract defines the response fields.
     * Responses:
     *  - 201: 提交计划审批 / Submit plan approval 的成功响应。 / Successful response for approvePlan.
     *  - 401: API error
     *  - 403: API error
     *  - 413: API error
     *  - 422: Validation error
     *  - 429: API error
     *  - 500: API error
     *  - 503: API error
     *
     * @param channelId Agent OpenAPI 渠道 UUID。 / Agent OpenAPI channel UUID.
     * @param planApprovalInput 提交计划审批 / Submit plan approval 的 JSON 请求参数。 / JSON request parameters for approvePlan.
     * @return [OperationResult]
     */
    @POST("api/agents/channel/openapi/v1/{channelId}/chat/plan/approve")
    suspend fun approvePlan(@Path("channelId") channelId: kotlin.String, @Body planApprovalInput: PlanApprovalInput): Response<OperationResult>

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/plan/{planId}/status
     * 查询计划审批状态 / Get plan approval status
     * 查询计划审批状态 / Get plan approval status 请求会在身份验签和资源归属校验后执行；响应字段以本契约为准。 / The request runs after signature and resource-ownership validation; this contract defines the response fields.
     * Responses:
     *  - 200: 查询计划审批状态 / Get plan approval status 的成功响应。 / Successful response for getPlanStatus.
     *  - 401: API error
     *  - 403: API error
     *  - 413: API error
     *  - 422: Validation error
     *  - 429: API error
     *  - 500: API error
     *  - 503: API error
     *
     * @param channelId Agent OpenAPI 渠道 UUID。 / Agent OpenAPI channel UUID.
     * @param planId 等待审批的计划 ID。 / Pending plan-approval ID.
     * @return [PlanStatus]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/plan/{planId}/status")
    suspend fun getPlanStatus(@Path("channelId") channelId: kotlin.String, @Path("planId") planId: kotlin.String): Response<PlanStatus>

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/user-input/{questionId}/status
     * 查询用户问答状态 / Get user-input status
     * 查询用户问答状态 / Get user-input status 请求会在身份验签和资源归属校验后执行；响应字段以本契约为准。 / The request runs after signature and resource-ownership validation; this contract defines the response fields.
     * Responses:
     *  - 200: 查询用户问答状态 / Get user-input status 的成功响应。 / Successful response for getUserInputStatus.
     *  - 401: API error
     *  - 403: API error
     *  - 413: API error
     *  - 422: Validation error
     *  - 429: API error
     *  - 500: API error
     *  - 503: API error
     *
     * @param channelId Agent OpenAPI 渠道 UUID。 / Agent OpenAPI channel UUID.
     * @param questionId 等待回答的问题 ID。 / Pending question ID.
     * @param conversationId 会话 ID；必须属于当前外部用户。 / Conversation ID owned by the current external user.
     * @param messageId 用户消息 ID；必须属于指定会话。 / User-message ID owned by the specified conversation.
     * @return [UserInputStatus]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/user-input/{questionId}/status")
    suspend fun getUserInputStatus(@Path("channelId") channelId: kotlin.String, @Path("questionId") questionId: kotlin.String, @Query("conversationId") conversationId: kotlin.String, @Query("messageId") messageId: kotlin.String): Response<UserInputStatus>

}

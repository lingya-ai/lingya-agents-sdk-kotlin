package cloud.lingya.agents.sdk.generated.api

import cloud.lingya.agents.sdk.generated.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.fasterxml.jackson.annotation.JsonProperty

import cloud.lingya.agents.sdk.generated.model.CodeMessage
import cloud.lingya.agents.sdk.generated.model.OperationResult
import cloud.lingya.agents.sdk.generated.model.PlanApprovalInput
import cloud.lingya.agents.sdk.generated.model.PlanStatus
import cloud.lingya.agents.sdk.generated.model.UserInputAnswerInput
import cloud.lingya.agents.sdk.generated.model.UserInputStatus
import cloud.lingya.agents.sdk.generated.model.ValidationError

interface InteractionsApi {
    /**
     * POST api/agents/channel/openapi/v1/{channelId}/chat/user-input/answer
     * 提交用户回答 / Submit a user answer
     * ### 使用场景 提交用户对 Agent 问题的选项或自定义回答。  ### Use case Submit selected options or custom input for an Agent question.  ### 前置条件 问题仍处于 pending 且标识与消息匹配。  ### Prerequisites The question is pending and identifiers match the message.  ### 行为与副作用 保存回答并可能推动执行继续。  ### Behavior and side effects Stores the answer and may resume execution.  ### 后续调用 继续监听 SSE 或查询消息状态。  ### Next step Continue listening to SSE or query message state.  ### 接口摘要 提交用户回答 / Submit a user answer  ### Operation summary 提交用户回答 / Submit a user answer
     * Responses:
     *  - 201: 提交用户回答 / Submit a user answer 的成功响应。 / Successful response for answerUserInput.
     *  - 401: 接口错误。 / API error.
     *  - 403: 接口错误。 / API error.
     *  - 413: 接口错误。 / API error.
     *  - 422: 参数校验错误。 / Validation error.
     *  - 429: 接口错误。 / API error.
     *  - 500: 接口错误。 / API error.
     *  - 503: 接口错误。 / API error.
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
     * ### 使用场景 响应 Agent 发出的计划审批请求。  ### Use case Respond to a plan-approval request emitted by the Agent.  ### 前置条件 从事件取得 planId、conversationId 和 messageId。  ### Prerequisites Obtain planId, conversationId, and messageId from the event.  ### 行为与副作用 提交批准或拒绝，可能推动执行继续。  ### Behavior and side effects Submits approval or rejection and may resume execution.  ### 后续调用 查询计划状态并继续监听事件。  ### Next step Check plan status and continue listening for events.  ### 接口摘要 提交计划审批 / Submit plan approval  ### Operation summary 提交计划审批 / Submit plan approval
     * Responses:
     *  - 201: 提交计划审批 / Submit plan approval 的成功响应。 / Successful response for approvePlan.
     *  - 401: 接口错误。 / API error.
     *  - 403: 接口错误。 / API error.
     *  - 413: 接口错误。 / API error.
     *  - 422: 参数校验错误。 / Validation error.
     *  - 429: 接口错误。 / API error.
     *  - 500: 接口错误。 / API error.
     *  - 503: 接口错误。 / API error.
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
     * ### 使用场景 确认计划是否仍在等待或已经处理。  ### Use case Check whether a plan is still pending or has been handled.  ### 前置条件 持有事件返回的 planId。  ### Prerequisites A planId returned by an event is available.  ### 行为与副作用 只读。  ### Behavior and side effects Read-only.  ### 后续调用 pending 时等待用户操作，否则刷新执行状态。  ### Next step Wait for user action while pending; otherwise refresh execution state.  ### 接口摘要 查询计划审批状态 / Get plan approval status  ### Operation summary 查询计划审批状态 / Get plan approval status
     * Responses:
     *  - 200: 查询计划审批状态 / Get plan approval status 的成功响应。 / Successful response for getPlanStatus.
     *  - 401: 接口错误。 / API error.
     *  - 403: 接口错误。 / API error.
     *  - 413: 接口错误。 / API error.
     *  - 422: 参数校验错误。 / Validation error.
     *  - 429: 接口错误。 / API error.
     *  - 500: 接口错误。 / API error.
     *  - 503: 接口错误。 / API error.
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
     * ### 使用场景 恢复页面时确认 Agent 问题是否仍待回答。  ### Use case Check whether an Agent question is still awaiting an answer after restoring a page.  ### 前置条件 questionId、conversationId 和 messageId 来自同一事件。  ### Prerequisites questionId, conversationId, and messageId come from the same event.  ### 行为与副作用 只读。  ### Behavior and side effects Read-only.  ### 后续调用 仍 pending 时展示问题，否则继续恢复事件。  ### Next step Show the question while pending; otherwise continue event recovery.  ### 接口摘要 查询用户问答状态 / Get user-input status  ### Operation summary 查询用户问答状态 / Get user-input status
     * Responses:
     *  - 200: 查询用户问答状态 / Get user-input status 的成功响应。 / Successful response for getUserInputStatus.
     *  - 401: 接口错误。 / API error.
     *  - 403: 接口错误。 / API error.
     *  - 413: 接口错误。 / API error.
     *  - 422: 参数校验错误。 / Validation error.
     *  - 429: 接口错误。 / API error.
     *  - 500: 接口错误。 / API error.
     *  - 503: 接口错误。 / API error.
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

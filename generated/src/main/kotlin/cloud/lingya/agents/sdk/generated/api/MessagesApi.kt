package cloud.lingya.agents.sdk.generated.api

import cloud.lingya.agents.sdk.generated.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.fasterxml.jackson.annotation.JsonProperty

import cloud.lingya.agents.sdk.generated.model.AgentAsyncTaskSync
import cloud.lingya.agents.sdk.generated.model.AsyncTask
import cloud.lingya.agents.sdk.generated.model.AsyncTaskPage
import cloud.lingya.agents.sdk.generated.model.CodeMessage
import cloud.lingya.agents.sdk.generated.model.ConversationMessage
import cloud.lingya.agents.sdk.generated.model.ConversationMessagePage
import cloud.lingya.agents.sdk.generated.model.SubagentTask
import cloud.lingya.agents.sdk.generated.model.SubagentTaskPage
import cloud.lingya.agents.sdk.generated.model.SubagentTaskResult
import cloud.lingya.agents.sdk.generated.model.SubagentTaskSync
import cloud.lingya.agents.sdk.generated.model.ValidationError

interface MessagesApi {
    /**
     * DELETE api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/subagents/{subagentTaskId}
     * 取消子 Agent 任务 / Cancel a subagent task
     * ### 使用场景 取消当前会话下仍可取消的子 Agent 任务。  ### Use case Cancel a subagent task that is still cancellable under the current conversation.  ### 前置条件 渠道凭证和外部用户已配置，目标资源属于当前用户。  ### Prerequisites Channel credentials and the external user are configured, and the target resource belongs to that user.  ### 行为与副作用 请求取消任务并返回最新任务状态。  ### Behavior and side effects Requests cancellation and returns the latest task status.  ### 后续调用 读取返回状态，并继续同步其他任务。  ### Next step Use the returned status and continue syncing other tasks.  ### 接口摘要 取消子 Agent 任务 / Cancel a subagent task  ### Operation summary 取消子 Agent 任务 / Cancel a subagent task
     * Responses:
     *  - 200: 取消子 Agent 任务 / Cancel a subagent task 的成功响应。 / Successful response for cancelConversationSubagent.
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
     * @param subagentTaskId 子 Agent 任务 ID。 / Subagent task ID.
     * @return [SubagentTask]
     */
    @DELETE("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/subagents/{subagentTaskId}")
    suspend fun cancelConversationSubagent(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Path("subagentTaskId") subagentTaskId: kotlin.String): Response<SubagentTask>

    /**
     * DELETE api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/messages/{messageId}/queue
     * 取消排队消息 / Cancel a queued message
     * ### 使用场景 在消息尚未开始执行时取消排队。  ### Use case Cancel a message before execution begins.  ### 前置条件 渠道凭证和外部用户已配置，目标资源属于当前用户。  ### Prerequisites Channel credentials and the external user are configured, and the target resource belongs to that user.  ### 行为与副作用 尝试把排队消息置为取消状态。  ### Behavior and side effects Attempts to move a queued message to cancelled state.  ### 后续调用 读取消息确认结果；执行中的消息改用 interrupt。  ### Next step Read the message to confirm; use interrupt for active execution.  ### 接口摘要 取消排队消息 / Cancel a queued message  ### Operation summary 取消排队消息 / Cancel a queued message
     * Responses:
     *  - 200: 取消排队消息 / Cancel a queued message 的成功响应。 / Successful response for cancelQueuedMessage.
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
     * @param messageId 用户消息 ID；必须属于指定会话。 / User-message ID owned by the specified conversation.
     * @return [ConversationMessage]
     */
    @DELETE("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/messages/{messageId}/queue")
    suspend fun cancelQueuedMessage(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Path("messageId") messageId: kotlin.String): Response<ConversationMessage>

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/async-tasks/{asyncTaskId}
     * 读取异步任务 / Get an asynchronous task
     * ### 使用场景 查看单个异步任务的进度与输出。  ### Use case Inspect progress and output for one asynchronous task.  ### 前置条件 渠道凭证和外部用户已配置，目标资源属于当前用户。  ### Prerequisites Channel credentials and the external user are configured, and the target resource belongs to that user.  ### 行为与副作用 只读。  ### Behavior and side effects Read-only.  ### 后续调用 完成后消费输出，失败时展示明确错误。  ### Next step Consume output on completion and show explicit errors on failure.  ### 接口摘要 读取异步任务 / Get an asynchronous task  ### Operation summary 读取异步任务 / Get an asynchronous task
     * Responses:
     *  - 200: 读取异步任务 / Get an asynchronous task 的成功响应。 / Successful response for getConversationAsyncTask.
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
     * @param asyncTaskId 异步任务 ID。 / Asynchronous task ID.
     * @return [AsyncTask]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/async-tasks/{asyncTaskId}")
    suspend fun getConversationAsyncTask(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Path("asyncTaskId") asyncTaskId: kotlin.String): Response<AsyncTask>

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/messages/{messageId}
     * 读取单条会话消息 / Get a conversation message
     * ### 使用场景 恢复或审计单条消息的完整状态。  ### Use case Recover or audit the complete state of one message.  ### 前置条件 渠道凭证和外部用户已配置，目标资源属于当前用户。  ### Prerequisites Channel credentials and the external user are configured, and the target resource belongs to that user.  ### 行为与副作用 只读。  ### Behavior and side effects Read-only.  ### 后续调用 根据状态读取事件、异步任务或工具结果。  ### Next step Read events, asynchronous tasks, or tool results according to status.  ### 接口摘要 读取单条会话消息 / Get a conversation message  ### Operation summary 读取单条会话消息 / Get a conversation message
     * Responses:
     *  - 200: 读取单条会话消息 / Get a conversation message 的成功响应。 / Successful response for getConversationMessage.
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
     * @param messageId 用户消息 ID；必须属于指定会话。 / User-message ID owned by the specified conversation.
     * @return [ConversationMessage]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/messages/{messageId}")
    suspend fun getConversationMessage(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Path("messageId") messageId: kotlin.String): Response<ConversationMessage>

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/subagents/{subagentTaskId}
     * 读取子 Agent 状态 / Get a subagent task
     * ### 使用场景 读取当前会话下一个子 Agent 的状态和结果可用性。  ### Use case Read one subagent&#39;s status and result availability under the current conversation.  ### 前置条件 渠道凭证和外部用户已配置，目标资源属于当前用户。  ### Prerequisites Channel credentials and the external user are configured, and the target resource belongs to that user.  ### 行为与副作用 只读。  ### Behavior and side effects Read-only.  ### 后续调用 resultAvailable 为 true 时读取结果接口。  ### Next step Read the result endpoint when resultAvailable is true.  ### 接口摘要 读取子 Agent 状态 / Get a subagent task  ### Operation summary 读取子 Agent 状态 / Get a subagent task
     * Responses:
     *  - 200: 读取子 Agent 状态 / Get a subagent task 的成功响应。 / Successful response for getConversationSubagent.
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
     * @param subagentTaskId 子 Agent 任务 ID。 / Subagent task ID.
     * @return [SubagentTask]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/subagents/{subagentTaskId}")
    suspend fun getConversationSubagent(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Path("subagentTaskId") subagentTaskId: kotlin.String): Response<SubagentTask>

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/subagents/{subagentTaskId}/result
     * 读取子 Agent 结果 / Get a subagent result
     * ### 使用场景 按需读取已完成子 Agent 的文本、文件和非文件制品。  ### Use case Read a completed subagent&#39;s text, file artifacts, and non-file artifacts on demand.  ### 前置条件 渠道凭证和外部用户已配置，目标资源属于当前用户。  ### Prerequisites Channel credentials and the external user are configured, and the target resource belongs to that user.  ### 行为与副作用 只读；完整结果仅通过该接口按需返回。  ### Behavior and side effects Read-only; the full result is returned only on demand through this endpoint.  ### 后续调用 按制品类型展示结果，并处理可恢复交付物。  ### Next step Display results by artifact type and handle recoverable deliverables.  ### 接口摘要 读取子 Agent 结果 / Get a subagent result  ### Operation summary 读取子 Agent 结果 / Get a subagent result
     * Responses:
     *  - 200: 读取子 Agent 结果 / Get a subagent result 的成功响应。 / Successful response for getConversationSubagentResult.
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
     * @param subagentTaskId 子 Agent 任务 ID。 / Subagent task ID.
     * @return [SubagentTaskResult]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/subagents/{subagentTaskId}/result")
    suspend fun getConversationSubagentResult(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Path("subagentTaskId") subagentTaskId: kotlin.String): Response<SubagentTaskResult>


    /**
    * enum for parameter orderDirection
    */
    enum class OrderDirectionListConversationAsyncTasks(val value: kotlin.String) {
            @JsonProperty(value = "ASC") ASC("ASC"),
            @JsonProperty(value = "DESC") DESC("DESC"),
    }


    /**
    * enum for parameter orderNullHandling
    */
    enum class OrderNullHandlingListConversationAsyncTasks(val value: kotlin.String) {
            @JsonProperty(value = "NATIVE") NATIVE("NATIVE"),
            @JsonProperty(value = "NULLS_FIRST") NULLS_FIRST("NULLS_FIRST"),
            @JsonProperty(value = "NULLS_LAST") NULLS_LAST("NULLS_LAST"),
    }

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/async-tasks
     * 分页查询异步任务 / List asynchronous tasks
     * ### 使用场景 分页查看会话产生的长任务。  ### Use case List long-running tasks produced by a conversation.  ### 前置条件 渠道凭证和外部用户已配置，目标资源属于当前用户。  ### Prerequisites Channel credentials and the external user are configured, and the target resource belongs to that user.  ### 行为与副作用 只读。  ### Behavior and side effects Read-only.  ### 后续调用 对未完成任务读取详情并轮询。  ### Next step Read and poll details for unfinished tasks.  ### 接口摘要 分页查询异步任务 / List asynchronous tasks  ### Operation summary 分页查询异步任务 / List asynchronous tasks
     * Responses:
     *  - 200: 分页查询异步任务 / List asynchronous tasks 的成功响应。 / Successful response for listConversationAsyncTasks.
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
     * @param current 从 0 开始的页码。 / Zero-based page index. (optional)
     * @param size 单页记录数。 / Number of records per page. (optional, default to 30)
     * @param orderBy 排序字段列表。 / Ordered list of sort fields. (optional)
     * @param orderDirection 排序方向。 / Sort direction. (optional, default to OrderDirection.ASC)
     * @param orderNullHandling 空值排序策略。 / Null ordering strategy. (optional, default to OrderNullHandling.NATIVE)
     * @param keyword 标题或正文检索关键字。 / Title or content search keyword. (optional)
     * @param status 状态过滤条件。 / Status filter. (optional)
     * @return [AsyncTaskPage]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/async-tasks")
    suspend fun listConversationAsyncTasks(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Query("current") current: kotlin.Int? = null, @Query("size") size: kotlin.Int? = 30, @Query("orderBy") orderBy: @JvmSuppressWildcards kotlin.collections.List<kotlin.String>? = null, @Query("orderDirection") orderDirection: OrderDirectionListConversationAsyncTasks? = OrderDirectionListConversationAsyncTasks.ASC, @Query("orderNullHandling") orderNullHandling: OrderNullHandlingListConversationAsyncTasks? = OrderNullHandlingListConversationAsyncTasks.NATIVE, @Query("keyword") keyword: kotlin.String? = null, @Query("status") status: @JvmSuppressWildcards kotlin.collections.List<kotlin.String>? = null): Response<AsyncTaskPage>


    /**
    * enum for parameter orderDirection
    */
    enum class OrderDirectionListConversationMessages(val value: kotlin.String) {
            @JsonProperty(value = "ASC") ASC("ASC"),
            @JsonProperty(value = "DESC") DESC("DESC"),
    }


    /**
    * enum for parameter orderNullHandling
    */
    enum class OrderNullHandlingListConversationMessages(val value: kotlin.String) {
            @JsonProperty(value = "NATIVE") NATIVE("NATIVE"),
            @JsonProperty(value = "NULLS_FIRST") NULLS_FIRST("NULLS_FIRST"),
            @JsonProperty(value = "NULLS_LAST") NULLS_LAST("NULLS_LAST"),
    }

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/messages
     * 分页查询会话消息 / List conversation messages
     * ### 使用场景 分页加载会话历史记录。  ### Use case Load conversation history page by page.  ### 前置条件 渠道凭证和外部用户已配置，目标资源属于当前用户。  ### Prerequisites Channel credentials and the external user are configured, and the target resource belongs to that user.  ### 行为与副作用 只读，返回消息分页。  ### Behavior and side effects Read-only and returns a message page.  ### 后续调用 选择消息后读取详情或事件。  ### Next step Read message detail or events after selection.  ### 接口摘要 分页查询会话消息 / List conversation messages  ### Operation summary 分页查询会话消息 / List conversation messages
     * Responses:
     *  - 200: 分页查询会话消息 / List conversation messages 的成功响应。 / Successful response for listConversationMessages.
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
     * @param current 从 0 开始的页码。 / Zero-based page index. (optional)
     * @param size 单页记录数。 / Number of records per page. (optional, default to 30)
     * @param orderBy 排序字段列表。 / Ordered list of sort fields. (optional)
     * @param orderDirection 排序方向。 / Sort direction. (optional, default to OrderDirection.ASC)
     * @param orderNullHandling 空值排序策略。 / Null ordering strategy. (optional, default to OrderNullHandling.NATIVE)
     * @param keyword 标题或正文检索关键字。 / Title or content search keyword. (optional)
     * @return [ConversationMessagePage]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/messages")
    suspend fun listConversationMessages(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Query("current") current: kotlin.Int? = null, @Query("size") size: kotlin.Int? = 30, @Query("orderBy") orderBy: @JvmSuppressWildcards kotlin.collections.List<kotlin.String>? = null, @Query("orderDirection") orderDirection: OrderDirectionListConversationMessages? = OrderDirectionListConversationMessages.ASC, @Query("orderNullHandling") orderNullHandling: OrderNullHandlingListConversationMessages? = OrderNullHandlingListConversationMessages.NATIVE, @Query("keyword") keyword: kotlin.String? = null): Response<ConversationMessagePage>


    /**
    * enum for parameter orderDirection
    */
    enum class OrderDirectionListConversationSubagents(val value: kotlin.String) {
            @JsonProperty(value = "ASC") ASC("ASC"),
            @JsonProperty(value = "DESC") DESC("DESC"),
    }


    /**
    * enum for parameter orderNullHandling
    */
    enum class OrderNullHandlingListConversationSubagents(val value: kotlin.String) {
            @JsonProperty(value = "NATIVE") NATIVE("NATIVE"),
            @JsonProperty(value = "NULLS_FIRST") NULLS_FIRST("NULLS_FIRST"),
            @JsonProperty(value = "NULLS_LAST") NULLS_LAST("NULLS_LAST"),
    }

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/subagents
     * 分页查询子 Agent 任务 / List subagent tasks
     * ### 使用场景 分页查看当前会话创建的子 Agent 任务。  ### Use case List subagent tasks created by the current conversation.  ### 前置条件 渠道凭证和外部用户已配置，目标资源属于当前用户。  ### Prerequisites Channel credentials and the external user are configured, and the target resource belongs to that user.  ### 行为与副作用 只读；返回任务状态分页。  ### Behavior and side effects Read-only; returns a paged task status list.  ### 后续调用 读取单个任务详情，或同步状态变化。  ### Next step Read an individual task or synchronize status changes.  ### 接口摘要 分页查询子 Agent 任务 / List subagent tasks  ### Operation summary 分页查询子 Agent 任务 / List subagent tasks
     * Responses:
     *  - 200: 分页查询子 Agent 任务 / List subagent tasks 的成功响应。 / Successful response for listConversationSubagents.
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
     * @param current 从 0 开始的页码。 / Zero-based page index. (optional)
     * @param size 单页记录数。 / Number of records per page. (optional, default to 30)
     * @param orderBy 排序字段列表。 / Ordered list of sort fields. (optional)
     * @param orderDirection 排序方向。 / Sort direction. (optional, default to OrderDirection.ASC)
     * @param orderNullHandling 空值排序策略。 / Null ordering strategy. (optional, default to OrderNullHandling.NATIVE)
     * @param keyword 标题或正文检索关键字。 / Title or content search keyword. (optional)
     * @param status 状态过滤条件。 / Status filter. (optional)
     * @return [SubagentTaskPage]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/subagents")
    suspend fun listConversationSubagents(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Query("current") current: kotlin.Int? = null, @Query("size") size: kotlin.Int? = 30, @Query("orderBy") orderBy: @JvmSuppressWildcards kotlin.collections.List<kotlin.String>? = null, @Query("orderDirection") orderDirection: OrderDirectionListConversationSubagents? = OrderDirectionListConversationSubagents.ASC, @Query("orderNullHandling") orderNullHandling: OrderNullHandlingListConversationSubagents? = OrderNullHandlingListConversationSubagents.NATIVE, @Query("keyword") keyword: kotlin.String? = null, @Query("status") status: @JvmSuppressWildcards kotlin.collections.List<kotlin.String>? = null): Response<SubagentTaskPage>

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/async-tasks/sync
     * 增量同步异步任务 / Sync asynchronous task updates
     * ### 使用场景 增量读取活动异步任务和游标之后的通知消息。  ### Use case Incrementally read active asynchronous tasks and notification messages after a cursor.  ### 前置条件 渠道凭证和外部用户已配置，目标资源属于当前用户。  ### Prerequisites Channel credentials and the external user are configured, and the target resource belongs to that user.  ### 行为与副作用 只读；返回活动任务、通知消息和下一消息游标。  ### Behavior and side effects Read-only; returns active tasks, notification messages, and the next message cursor.  ### 后续调用 保存 nextMessageId；pollingRequired 为 true 时继续同步。  ### Next step Save nextMessageId and continue syncing when pollingRequired is true.  ### 接口摘要 增量同步异步任务 / Sync asynchronous task updates  ### Operation summary 增量同步异步任务 / Sync asynchronous task updates
     * Responses:
     *  - 200: 增量同步异步任务 / Sync asynchronous task updates 的成功响应。 / Successful response for syncConversationAsyncTasks.
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
     * @param afterMessageId 通知消息同步游标。 / Notification-message sync cursor. (optional)
     * @param size 单页记录数。 / Number of records per page. (optional, default to 50)
     * @return [AgentAsyncTaskSync]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/async-tasks/sync")
    suspend fun syncConversationAsyncTasks(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Query("afterMessageId") afterMessageId: kotlin.String? = null, @Query("size") size: kotlin.Int? = 50): Response<AgentAsyncTaskSync>

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/subagents/sync
     * 增量同步子 Agent 状态 / Sync subagent task updates
     * ### 使用场景 通过游标增量读取子 Agent 状态和未读终态结果。  ### Use case Incrementally read subagent status changes and unread terminal results using a cursor.  ### 前置条件 渠道凭证和外部用户已配置，目标资源属于当前用户。  ### Prerequisites Channel credentials and the external user are configured, and the target resource belongs to that user.  ### 行为与副作用 只读；游标应原样保存并用于下一次同步。  ### Behavior and side effects Read-only; preserve the cursor unchanged for the next sync.  ### 后续调用 保存 nextCursor；pollingRequired 为 true 时继续同步。  ### Next step Save nextCursor and continue syncing when pollingRequired is true.  ### 接口摘要 增量同步子 Agent 状态 / Sync subagent task updates  ### Operation summary 增量同步子 Agent 状态 / Sync subagent task updates
     * Responses:
     *  - 200: 增量同步子 Agent 状态 / Sync subagent task updates 的成功响应。 / Successful response for syncConversationSubagents.
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
     * @param cursor 子 Agent 状态同步游标。 / Subagent-state sync cursor. (optional)
     * @param size 单页记录数。 / Number of records per page. (optional, default to 50)
     * @return [SubagentTaskSync]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/subagents/sync")
    suspend fun syncConversationSubagents(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Query("cursor") cursor: kotlin.String? = null, @Query("size") size: kotlin.Int? = 50): Response<SubagentTaskSync>

}

package cloud.lingya.agents.sdk.generated.api

import cloud.lingya.agents.sdk.generated.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.fasterxml.jackson.annotation.JsonProperty

import cloud.lingya.agents.sdk.generated.model.AsyncTask
import cloud.lingya.agents.sdk.generated.model.AsyncTaskPage
import cloud.lingya.agents.sdk.generated.model.CodeMessage
import cloud.lingya.agents.sdk.generated.model.ConversationMessage
import cloud.lingya.agents.sdk.generated.model.ConversationMessagePage
import cloud.lingya.agents.sdk.generated.model.ValidationError

interface MessagesApi {
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

}

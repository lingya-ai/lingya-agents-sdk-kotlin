package cloud.lingya.agents.sdk.generated.api

import cloud.lingya.agents.sdk.generated.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.fasterxml.jackson.annotation.JsonProperty

import cloud.lingya.agents.sdk.generated.model.CodeMessage
import cloud.lingya.agents.sdk.generated.model.ConversationActivityBatchInput
import cloud.lingya.agents.sdk.generated.model.ConversationActivityList
import cloud.lingya.agents.sdk.generated.model.ConversationContextUsage
import cloud.lingya.agents.sdk.generated.model.ConversationGroup
import cloud.lingya.agents.sdk.generated.model.ConversationGroupDeleted
import cloud.lingya.agents.sdk.generated.model.ConversationGroupList
import cloud.lingya.agents.sdk.generated.model.ConversationGroupNameInput
import cloud.lingya.agents.sdk.generated.model.ConversationIds
import cloud.lingya.agents.sdk.generated.model.ConversationMetadata
import cloud.lingya.agents.sdk.generated.model.ConversationReadReceipt
import cloud.lingya.agents.sdk.generated.model.ConversationReadReceiptInput
import cloud.lingya.agents.sdk.generated.model.ConversationStats
import cloud.lingya.agents.sdk.generated.model.ConversationStatusInput
import cloud.lingya.agents.sdk.generated.model.ConversationSummary
import cloud.lingya.agents.sdk.generated.model.ConversationSummaryList
import cloud.lingya.agents.sdk.generated.model.ConversationTitle
import cloud.lingya.agents.sdk.generated.model.ConversationTitleInput
import cloud.lingya.agents.sdk.generated.model.UpdateConversationGroupInput
import cloud.lingya.agents.sdk.generated.model.ValidationError

interface ConversationsApi {
    /**
     * POST api/agents/channel/openapi/v1/{channelId}/chat/conversation-groups
     * 创建会话分组 / Create conversation group
     * ### 使用场景 去除名称首尾空白后创建分组，名称最多 60 字符。  ### Use case Create a group after trimming the name; names are limited to 60 characters.  ### 前置条件 有效渠道身份；会话资源必须属于当前用户。  ### Prerequisites Valid channel identity; conversation resources must belong to the current user.  ### 行为与副作用 去除名称首尾空白后创建分组，名称最多 60 字符。  ### Behavior and side effects Create a group after trimming the name; names are limited to 60 characters.  ### 后续调用 根据返回的权威状态更新客户端。  ### Next step Update the client using the returned authoritative state.  ### 接口摘要 创建会话分组 / Create conversation group  ### Operation summary 创建会话分组 / Create conversation group
     * Responses:
     *  - 201: 创建会话分组 / Create conversation group 的成功响应。 / Successful response for createConversationGroup.
     *  - 401: 接口错误。 / API error.
     *  - 403: 接口错误。 / API error.
     *  - 413: 接口错误。 / API error.
     *  - 422: 参数校验错误。 / Validation error.
     *  - 429: 接口错误。 / API error.
     *  - 500: 接口错误。 / API error.
     *  - 503: 接口错误。 / API error.
     *
     * @param channelId Agent OpenAPI 渠道 UUID。 / Agent OpenAPI channel UUID.
     * @param conversationGroupNameInput 创建会话分组 / Create conversation group 的 JSON 请求参数。 / JSON request parameters for createConversationGroup.
     * @return [ConversationGroup]
     */
    @POST("api/agents/channel/openapi/v1/{channelId}/chat/conversation-groups")
    suspend fun createConversationGroup(@Path("channelId") channelId: kotlin.String, @Body conversationGroupNameInput: ConversationGroupNameInput): Response<ConversationGroup>

    /**
     * DELETE api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}
     * 删除会话 / Delete a conversation
     * ### 使用场景 永久移除用户不再需要的会话。  ### Use case Permanently remove a conversation the user no longer needs.  ### 前置条件 渠道凭证和外部用户已配置，目标资源属于当前用户。  ### Prerequisites Channel credentials and the external user are configured, and the target resource belongs to that user.  ### 行为与副作用 删除会话及其可见关联数据。  ### Behavior and side effects Deletes the conversation and its visible related data.  ### 后续调用 从本地列表和缓存中移除该会话。  ### Next step Remove the conversation from local lists and caches.  ### 接口摘要 删除会话 / Delete a conversation  ### Operation summary 删除会话 / Delete a conversation
     * Responses:
     *  - 200: 删除会话 / Delete a conversation 的成功响应。 / Successful response for deleteConversation.
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
     * @return [Unit]
     */
    @DELETE("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}")
    suspend fun deleteConversation(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String): Response<Unit>

    /**
     * DELETE api/agents/channel/openapi/v1/{channelId}/chat/conversation-groups/{groupId}
     * 删除会话分组 / Delete conversation group
     * ### 使用场景 删除分组并将会话移回未分组，返回移动数量。  ### Use case Delete the group and move its conversations to ungrouped; return the moved count.  ### 前置条件 有效渠道身份；会话资源必须属于当前用户。  ### Prerequisites Valid channel identity; conversation resources must belong to the current user.  ### 行为与副作用 删除分组并将会话移回未分组，返回移动数量。  ### Behavior and side effects Delete the group and move its conversations to ungrouped; return the moved count.  ### 后续调用 根据返回的权威状态更新客户端。  ### Next step Update the client using the returned authoritative state.  ### 接口摘要 删除会话分组 / Delete conversation group  ### Operation summary 删除会话分组 / Delete conversation group
     * Responses:
     *  - 200: 删除会话分组 / Delete conversation group 的成功响应。 / Successful response for deleteConversationGroup.
     *  - 401: 接口错误。 / API error.
     *  - 403: 接口错误。 / API error.
     *  - 413: 接口错误。 / API error.
     *  - 422: 参数校验错误。 / Validation error.
     *  - 429: 接口错误。 / API error.
     *  - 500: 接口错误。 / API error.
     *  - 503: 接口错误。 / API error.
     *
     * @param channelId Agent OpenAPI 渠道 UUID。 / Agent OpenAPI channel UUID.
     * @param groupId 字段 groupId / group id field。
     * @return [ConversationGroupDeleted]
     */
    @DELETE("api/agents/channel/openapi/v1/{channelId}/chat/conversation-groups/{groupId}")
    suspend fun deleteConversationGroup(@Path("channelId") channelId: kotlin.String, @Path("groupId") groupId: kotlin.Long): Response<ConversationGroupDeleted>

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/context-usage
     * 读取上下文占用 / Get context usage
     * ### 使用场景 决定是否需要压缩上下文或提示容量时读取占用。  ### Use case Read context usage to decide whether to compact or warn about capacity.  ### 前置条件 渠道凭证和外部用户已配置，目标资源属于当前用户。  ### Prerequisites Channel credentials and the external user are configured, and the target resource belongs to that user.  ### 行为与副作用 只读。  ### Behavior and side effects Read-only.  ### 后续调用 达到阈值时调用 compactConversation。  ### Next step Call compactConversation when the threshold is reached.  ### 接口摘要 读取上下文占用 / Get context usage  ### Operation summary 读取上下文占用 / Get context usage
     * Responses:
     *  - 200: 读取上下文占用 / Get context usage 的成功响应。 / Successful response for getConversationContextUsage.
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
     * @return [ConversationContextUsage]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/context-usage")
    suspend fun getConversationContextUsage(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String): Response<ConversationContextUsage>

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations/stats
     * 读取会话统计 / Get conversation statistics
     * ### 使用场景 展示当前用户在渠道中的会话聚合统计。  ### Use case Display aggregate conversation statistics for the current user in the channel.  ### 前置条件 外部用户已配置。  ### Prerequisites The external user is configured.  ### 行为与副作用 只读。  ### Behavior and side effects Read-only.  ### 后续调用 用于仪表盘展示，不作为逐会话状态来源。  ### Next step Use for dashboards, not as per-conversation state.  ### 接口摘要 读取会话统计 / Get conversation statistics  ### Operation summary 读取会话统计 / Get conversation statistics
     * Responses:
     *  - 200: 读取会话统计 / Get conversation statistics 的成功响应。 / Successful response for getConversationStats.
     *  - 401: 接口错误。 / API error.
     *  - 403: 接口错误。 / API error.
     *  - 413: 接口错误。 / API error.
     *  - 422: 参数校验错误。 / Validation error.
     *  - 429: 接口错误。 / API error.
     *  - 500: 接口错误。 / API error.
     *  - 503: 接口错误。 / API error.
     *
     * @param channelId Agent OpenAPI 渠道 UUID。 / Agent OpenAPI channel UUID.
     * @return [ConversationStats]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/stats")
    suspend fun getConversationStats(@Path("channelId") channelId: kotlin.String): Response<ConversationStats>

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}
     * 读取会话摘要 / Get conversation summary
     * ### 使用场景 按会话标识恢复当前用户的会话摘要。  ### Use case Restore the current user&#39;s conversation summary by ID.  ### 前置条件 有效渠道身份；会话资源必须属于当前用户。  ### Prerequisites Valid channel identity; conversation resources must belong to the current user.  ### 行为与副作用 按会话标识恢复当前用户的会话摘要。  ### Behavior and side effects Restore the current user&#39;s conversation summary by ID.  ### 后续调用 根据返回的权威状态更新客户端。  ### Next step Update the client using the returned authoritative state.  ### 接口摘要 读取会话摘要 / Get conversation summary  ### Operation summary 读取会话摘要 / Get conversation summary
     * Responses:
     *  - 200: 读取会话摘要 / Get conversation summary 的成功响应。 / Successful response for getConversationSummary.
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
     * @return [ConversationSummary]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}")
    suspend fun getConversationSummary(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String): Response<ConversationSummary>

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/title
     * 读取会话标题 / Get conversation title
     * ### 使用场景 读取自动生成或用户设置的会话标题。  ### Use case Read the generated or user-defined conversation title.  ### 前置条件 渠道凭证和外部用户已配置，目标资源属于当前用户。  ### Prerequisites Channel credentials and the external user are configured, and the target resource belongs to that user.  ### 行为与副作用 只读。  ### Behavior and side effects Read-only.  ### 后续调用 生成中时稍后重试，或允许用户更新标题。  ### Next step Retry while generation is pending or let the user update the title.  ### 接口摘要 读取会话标题 / Get conversation title  ### Operation summary 读取会话标题 / Get conversation title
     * Responses:
     *  - 200: 读取会话标题 / Get conversation title 的成功响应。 / Successful response for getConversationTitle.
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
     * @return [ConversationTitle]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/title")
    suspend fun getConversationTitle(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String): Response<ConversationTitle>

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations/active
     * 查询活动会话 / List active conversations
     * ### 使用场景 快速恢复当前仍在执行的会话。  ### Use case Quickly recover conversations that are still executing.  ### 前置条件 外部用户已配置。  ### Prerequisites The external user is configured.  ### 行为与副作用 只读，返回会话 ID。  ### Behavior and side effects Read-only and returns conversation IDs.  ### 后续调用 批量查询活动详情或重新连接 SSE。  ### Next step Batch-query activity details or reconnect SSE.  ### 接口摘要 查询活动会话 / List active conversations  ### Operation summary 查询活动会话 / List active conversations
     * Responses:
     *  - 200: 查询活动会话 / List active conversations 的成功响应。 / Successful response for listActiveConversations.
     *  - 401: 接口错误。 / API error.
     *  - 403: 接口错误。 / API error.
     *  - 413: 接口错误。 / API error.
     *  - 422: 参数校验错误。 / Validation error.
     *  - 429: 接口错误。 / API error.
     *  - 500: 接口错误。 / API error.
     *  - 503: 接口错误。 / API error.
     *
     * @param channelId Agent OpenAPI 渠道 UUID。 / Agent OpenAPI channel UUID.
     * @return [ConversationIds]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/active")
    suspend fun listActiveConversations(@Path("channelId") channelId: kotlin.String): Response<ConversationIds>

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversation-groups
     * 查询会话分组 / List conversation groups
     * ### 使用场景 一次读取当前用户作用域下的完整分组目录。  ### Use case Read the complete group directory for the current user.  ### 前置条件 有效渠道身份；会话资源必须属于当前用户。  ### Prerequisites Valid channel identity; conversation resources must belong to the current user.  ### 行为与副作用 一次读取当前用户作用域下的完整分组目录。  ### Behavior and side effects Read the complete group directory for the current user.  ### 后续调用 根据返回的权威状态更新客户端。  ### Next step Update the client using the returned authoritative state.  ### 接口摘要 查询会话分组 / List conversation groups  ### Operation summary 查询会话分组 / List conversation groups
     * Responses:
     *  - 200: 查询会话分组 / List conversation groups 的成功响应。 / Successful response for listConversationGroups.
     *  - 401: 接口错误。 / API error.
     *  - 403: 接口错误。 / API error.
     *  - 413: 接口错误。 / API error.
     *  - 422: 参数校验错误。 / Validation error.
     *  - 429: 接口错误。 / API error.
     *  - 500: 接口错误。 / API error.
     *  - 503: 接口错误。 / API error.
     *
     * @param channelId Agent OpenAPI 渠道 UUID。 / Agent OpenAPI channel UUID.
     * @return [ConversationGroupList]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversation-groups")
    suspend fun listConversationGroups(@Path("channelId") channelId: kotlin.String): Response<ConversationGroupList>


    /**
    * enum for parameter status
    */
    enum class StatusListConversations(val value: kotlin.String) {
            @JsonProperty(value = "ACTIVE") ACTIVE("ACTIVE"),
            @JsonProperty(value = "ARCHIVED") ARCHIVED("ARCHIVED"),
            @JsonProperty(value = "ALL") ALL("ALL"),
    }


    /**
    * enum for parameter groupFilter
    */
    enum class GroupFilterListConversations(val value: kotlin.String) {
            @JsonProperty(value = "ALL") ALL("ALL"),
            @JsonProperty(value = "UNGROUPED") UNGROUPED("UNGROUPED"),
            @JsonProperty(value = "GROUP") GROUP("GROUP"),
    }

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations
     * 分页查询会话 / List conversations
     * ### 使用场景 构建用户的分页会话列表与搜索结果。  ### Use case Build the user&#39;s paged conversation list and search results.  ### 前置条件 外部用户已配置。  ### Prerequisites The external user is configured.  ### 行为与副作用 只读，返回分页记录。  ### Behavior and side effects Read-only and returns paged records.  ### 后续调用 按需读取标题、活动状态或消息。  ### Next step Read titles, activity, or messages as needed.  ### 接口摘要 分页查询会话 / List conversations  ### Operation summary 分页查询会话 / List conversations
     * Responses:
     *  - 200: 分页查询会话 / List conversations 的成功响应。 / Successful response for listConversations.
     *  - 401: 接口错误。 / API error.
     *  - 403: 接口错误。 / API error.
     *  - 413: 接口错误。 / API error.
     *  - 422: 参数校验错误。 / Validation error.
     *  - 429: 接口错误。 / API error.
     *  - 500: 接口错误。 / API error.
     *  - 503: 接口错误。 / API error.
     *
     * @param channelId Agent OpenAPI 渠道 UUID。 / Agent OpenAPI channel UUID.
     * @param current 从 0 开始的页码。 / Zero-based page index. (optional, default to 0)
     * @param size 单页记录数。 / Number of records per page. (optional, default to 30)
     * @param status 状态过滤条件。 / Status filter. (optional, default to Status.ACTIVE)
     * @param keyword 标题或正文检索关键字。 / Title or content search keyword. (optional)
     * @param groupFilter 字段 groupFilter / group filter field。 (optional, default to GroupFilter.ALL)
     * @param groupId groupFilter 为 GROUP 时必填。 / Required when groupFilter is GROUP. (optional)
     * @return [ConversationSummaryList]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations")
    suspend fun listConversations(@Path("channelId") channelId: kotlin.String, @Query("current") current: kotlin.Int? = 0, @Query("size") size: kotlin.Int? = 30, @Query("status") status: StatusListConversations? = StatusListConversations.ACTIVE, @Query("keyword") keyword: kotlin.String? = null, @Query("groupFilter") groupFilter: GroupFilterListConversations? = GroupFilterListConversations.ALL, @Query("groupId") groupId: kotlin.Long? = null): Response<ConversationSummaryList>

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations/unread
     * 查询未读会话 / List unread conversations
     * ### 使用场景 显示存在未读完成结果的会话角标。  ### Use case Display conversations with unread completed results.  ### 前置条件 外部用户已配置。  ### Prerequisites The external user is configured.  ### 行为与副作用 只读，返回未读会话 ID。  ### Behavior and side effects Read-only and returns unread conversation IDs.  ### 后续调用 读取消息后推进已读游标。  ### Next step Advance the read cursor after reading messages.  ### 接口摘要 查询未读会话 / List unread conversations  ### Operation summary 查询未读会话 / List unread conversations
     * Responses:
     *  - 200: 查询未读会话 / List unread conversations 的成功响应。 / Successful response for listUnreadConversations.
     *  - 401: 接口错误。 / API error.
     *  - 403: 接口错误。 / API error.
     *  - 413: 接口错误。 / API error.
     *  - 422: 参数校验错误。 / Validation error.
     *  - 429: 接口错误。 / API error.
     *  - 500: 接口错误。 / API error.
     *  - 503: 接口错误。 / API error.
     *
     * @param channelId Agent OpenAPI 渠道 UUID。 / Agent OpenAPI channel UUID.
     * @return [ConversationIds]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/unread")
    suspend fun listUnreadConversations(@Path("channelId") channelId: kotlin.String): Response<ConversationIds>

    /**
     * PUT api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/read-receipt
     * 推进会话已读游标 / Mark a conversation as read
     * ### 使用场景 用户查看结果后推进会话已读位置。  ### Use case Advance a conversation&#39;s read position after the user views a result.  ### 前置条件 渠道凭证和外部用户已配置，目标资源属于当前用户。  ### Prerequisites Channel credentials and the external user are configured, and the target resource belongs to that user.  ### 行为与副作用 更新已读游标，不删除消息。  ### Behavior and side effects Updates the read cursor without deleting messages.  ### 后续调用 刷新未读会话列表。  ### Next step Refresh the unread conversation list.  ### 接口摘要 推进会话已读游标 / Mark a conversation as read  ### Operation summary 推进会话已读游标 / Mark a conversation as read
     * Responses:
     *  - 200: 推进会话已读游标 / Mark a conversation as read 的成功响应。 / Successful response for markConversationRead.
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
     * @param conversationReadReceiptInput 推进会话已读游标 / Mark a conversation as read 的 JSON 请求参数。 / JSON request parameters for markConversationRead.
     * @return [ConversationReadReceipt]
     */
    @PUT("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/read-receipt")
    suspend fun markConversationRead(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Body conversationReadReceiptInput: ConversationReadReceiptInput): Response<ConversationReadReceipt>

    /**
     * PATCH api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/group
     * 移动会话分组 / Move conversation to group
     * ### 使用场景 必须显式提供 groupId；null 表示移回未分组。  ### Use case Supply groupId explicitly; null moves the conversation to ungrouped.  ### 前置条件 有效渠道身份；会话资源必须属于当前用户。  ### Prerequisites Valid channel identity; conversation resources must belong to the current user.  ### 行为与副作用 必须显式提供 groupId；null 表示移回未分组。  ### Behavior and side effects Supply groupId explicitly; null moves the conversation to ungrouped.  ### 后续调用 根据返回的权威状态更新客户端。  ### Next step Update the client using the returned authoritative state.  ### 接口摘要 移动会话分组 / Move conversation to group  ### Operation summary 移动会话分组 / Move conversation to group
     * Responses:
     *  - 200: 移动会话分组 / Move conversation to group 的成功响应。 / Successful response for moveConversationToGroup.
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
     * @param updateConversationGroupInput 移动会话分组 / Move conversation to group 的 JSON 请求参数。 / JSON request parameters for moveConversationToGroup.
     * @return [ConversationMetadata]
     */
    @PATCH("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/group")
    suspend fun moveConversationToGroup(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Body updateConversationGroupInput: UpdateConversationGroupInput): Response<ConversationMetadata>

    /**
     * POST api/agents/channel/openapi/v1/{channelId}/chat/conversations/activity/query
     * 批量查询会话活动 / Query conversation activities
     * ### 使用场景 批量刷新多个会话的执行与未读状态。  ### Use case Refresh execution and unread state for multiple conversations in one request.  ### 前置条件 已持有最多 1000 个当前用户的会话 ID。  ### Prerequisites Up to 1000 conversation IDs for the current user are available.  ### 行为与副作用 只读，返回每个会话的活动投影。  ### Behavior and side effects Read-only and returns an activity projection per conversation.  ### 后续调用 更新列表状态并恢复必要的流。  ### Next step Update list state and recover any required streams.  ### 接口摘要 批量查询会话活动 / Query conversation activities  ### Operation summary 批量查询会话活动 / Query conversation activities
     * Responses:
     *  - 200: 批量查询会话活动 / Query conversation activities 的成功响应。 / Successful response for queryConversationActivities.
     *  - 401: 接口错误。 / API error.
     *  - 403: 接口错误。 / API error.
     *  - 413: 接口错误。 / API error.
     *  - 422: 参数校验错误。 / Validation error.
     *  - 429: 接口错误。 / API error.
     *  - 500: 接口错误。 / API error.
     *  - 503: 接口错误。 / API error.
     *
     * @param channelId Agent OpenAPI 渠道 UUID。 / Agent OpenAPI channel UUID.
     * @param conversationActivityBatchInput 批量查询会话活动 / Query conversation activities 的 JSON 请求参数。 / JSON request parameters for queryConversationActivities.
     * @return [ConversationActivityList]
     */
    @POST("api/agents/channel/openapi/v1/{channelId}/chat/conversations/activity/query")
    suspend fun queryConversationActivities(@Path("channelId") channelId: kotlin.String, @Body conversationActivityBatchInput: ConversationActivityBatchInput): Response<ConversationActivityList>

    /**
     * PATCH api/agents/channel/openapi/v1/{channelId}/chat/conversation-groups/{groupId}
     * 重命名会话分组 / Rename conversation group
     * ### 使用场景 更新当前用户的分组名称。  ### Use case Update the name of a group owned by the current user.  ### 前置条件 有效渠道身份；会话资源必须属于当前用户。  ### Prerequisites Valid channel identity; conversation resources must belong to the current user.  ### 行为与副作用 更新当前用户的分组名称。  ### Behavior and side effects Update the name of a group owned by the current user.  ### 后续调用 根据返回的权威状态更新客户端。  ### Next step Update the client using the returned authoritative state.  ### 接口摘要 重命名会话分组 / Rename conversation group  ### Operation summary 重命名会话分组 / Rename conversation group
     * Responses:
     *  - 200: 重命名会话分组 / Rename conversation group 的成功响应。 / Successful response for renameConversationGroup.
     *  - 401: 接口错误。 / API error.
     *  - 403: 接口错误。 / API error.
     *  - 413: 接口错误。 / API error.
     *  - 422: 参数校验错误。 / Validation error.
     *  - 429: 接口错误。 / API error.
     *  - 500: 接口错误。 / API error.
     *  - 503: 接口错误。 / API error.
     *
     * @param channelId Agent OpenAPI 渠道 UUID。 / Agent OpenAPI channel UUID.
     * @param groupId 字段 groupId / group id field。
     * @param conversationGroupNameInput 重命名会话分组 / Rename conversation group 的 JSON 请求参数。 / JSON request parameters for renameConversationGroup.
     * @return [ConversationGroup]
     */
    @PATCH("api/agents/channel/openapi/v1/{channelId}/chat/conversation-groups/{groupId}")
    suspend fun renameConversationGroup(@Path("channelId") channelId: kotlin.String, @Path("groupId") groupId: kotlin.Long, @Body conversationGroupNameInput: ConversationGroupNameInput): Response<ConversationGroup>

    /**
     * PATCH api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/status
     * 更新会话状态 / Update conversation status
     * ### 使用场景 归档、恢复或切换会话业务状态。  ### Use case Archive, restore, or otherwise change conversation business status.  ### 前置条件 渠道凭证和外部用户已配置，目标资源属于当前用户。  ### Prerequisites Channel credentials and the external user are configured, and the target resource belongs to that user.  ### 行为与副作用 异步更新会话状态。  ### Behavior and side effects Updates conversation status asynchronously.  ### 后续调用 按新状态刷新会话列表。  ### Next step Refresh conversation lists using the new status.  ### 接口摘要 更新会话状态 / Update conversation status  ### Operation summary 更新会话状态 / Update conversation status
     * Responses:
     *  - 202: 更新会话状态 / Update conversation status 的成功响应。 / Successful response for updateConversationStatus.
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
     * @param conversationStatusInput 更新会话状态 / Update conversation status 的 JSON 请求参数。 / JSON request parameters for updateConversationStatus.
     * @return [Unit]
     */
    @PATCH("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/status")
    suspend fun updateConversationStatus(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Body conversationStatusInput: ConversationStatusInput): Response<Unit>

    /**
     * PATCH api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/title
     * 更新会话标题 / Update conversation title
     * ### 使用场景 保存用户编辑的会话标题。  ### Use case Save a user-edited conversation title.  ### 前置条件 渠道凭证和外部用户已配置，目标资源属于当前用户。  ### Prerequisites Channel credentials and the external user are configured, and the target resource belongs to that user.  ### 行为与副作用 同步更新标题并返回权威会话元数据。  ### Behavior and side effects Update the title and return authoritative conversation metadata.  ### 后续调用 重新读取标题或刷新列表。  ### Next step Read the title again or refresh the list.  ### 接口摘要 更新会话标题 / Update conversation title  ### Operation summary 更新会话标题 / Update conversation title
     * Responses:
     *  - 200: 更新会话标题 / Update conversation title 的成功响应。 / Successful response for updateConversationTitle.
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
     * @param conversationTitleInput 更新会话标题 / Update conversation title 的 JSON 请求参数。 / JSON request parameters for updateConversationTitle.
     * @return [ConversationMetadata]
     */
    @PATCH("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/title")
    suspend fun updateConversationTitle(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Body conversationTitleInput: ConversationTitleInput): Response<ConversationMetadata>

}

package cloud.lingya.agents.sdk.api

import cloud.lingya.agents.sdk.AgentsUserClient
import cloud.lingya.agents.sdk.bodyOrThrow
import cloud.lingya.agents.sdk.RequestOptions
import cloud.lingya.agents.sdk.DownloadResponse
import cloud.lingya.agents.sdk.filename
import cloud.lingya.agents.sdk.generated.api.ConversationsApi as GeneratedConversationsApi
import cloud.lingya.agents.sdk.generated.model.*
import kotlinx.coroutines.flow.Flow

/**
 * conversations 分组的 channel 绑定异步接口。 / Channel-bound asynchronous conversations operations.
 *
 * `channelId` 来自根客户端，避免调用方法时传入与签名目标不一致的 channel。
 * / `channelId` comes from the root client so method calls cannot diverge from the signed channel.
 *
 * @author 思追(shaco)
 */
public class ConversationsApi internal constructor(
    private val channelId: String,
    private val delegate: GeneratedConversationsApi,
    private val userClient: AgentsUserClient,
) {
    /**
     * 删除会话 / Delete a conversation
     *
     * @param conversationId 会话 ID；必须属于当前外部用户。 / Conversation ID owned by the current external user.
     * @return 契约定义的强类型响应。 / The typed response defined by the contract.
     */
    public suspend fun deleteConversation(
        conversationId: kotlin.String,
    ): Unit = deleteConversation(conversationId, RequestOptions())
    public suspend fun deleteConversation(
        conversationId: kotlin.String,
        requestOptions: RequestOptions,
    ): Unit =
        userClient.execute(requestOptions) { api -> api.conversations.deleteConversation(channelId, conversationId).bodyOrThrow() }

    /**
     * 读取会话摘要 / Get conversation summary
     *
     * @param conversationId 会话 ID；必须属于当前外部用户。 / Conversation ID owned by the current external user.
     * @return 契约定义的强类型响应。 / The typed response defined by the contract.
     */
    public suspend fun getConversationSummary(
        conversationId: kotlin.String,
    ): ConversationSummary = getConversationSummary(conversationId, RequestOptions())
    public suspend fun getConversationSummary(
        conversationId: kotlin.String,
        requestOptions: RequestOptions,
    ): ConversationSummary =
        userClient.execute(requestOptions) { api -> api.conversations.getConversationSummary(channelId, conversationId).bodyOrThrow() }

    /**
     * 读取上下文占用 / Get context usage
     *
     * @param conversationId 会话 ID；必须属于当前外部用户。 / Conversation ID owned by the current external user.
     * @return 契约定义的强类型响应。 / The typed response defined by the contract.
     */
    public suspend fun getConversationContextUsage(
        conversationId: kotlin.String,
    ): ConversationContextUsage = getConversationContextUsage(conversationId, RequestOptions())
    public suspend fun getConversationContextUsage(
        conversationId: kotlin.String,
        requestOptions: RequestOptions,
    ): ConversationContextUsage =
        userClient.execute(requestOptions) { api -> api.conversations.getConversationContextUsage(channelId, conversationId).bodyOrThrow() }

    /**
     * 分页查询会话 / List conversations
     *
     * @param current 从 0 开始的页码。 / Zero-based page index.
     * @param size 单页记录数。 / Number of records per page.
     * @param status 状态过滤条件。 / Status filter.
     * @param keyword 标题或正文检索关键字。 / Title or content search keyword.
     * @param groupFilter 字段 groupFilter / group filter field。
     * @param groupId groupFilter 为 GROUP 时必填。 / Required when groupFilter is GROUP.
     * @return 契约定义的强类型响应。 / The typed response defined by the contract.
     */
    public suspend fun listConversations(
        current: kotlin.Int? = 0,
        size: kotlin.Int? = 30,
        status: GeneratedConversationsApi.StatusListConversations? = GeneratedConversationsApi.StatusListConversations.ACTIVE,
        keyword: kotlin.String? = null,
        groupFilter: GeneratedConversationsApi.GroupFilterListConversations? = GeneratedConversationsApi.GroupFilterListConversations.ALL,
        groupId: kotlin.Long? = null,
    ): ConversationSummaryList = listConversations(current, size, status, keyword, groupFilter, groupId, RequestOptions())
    public suspend fun listConversations(
        current: kotlin.Int? = 0,
        size: kotlin.Int? = 30,
        status: GeneratedConversationsApi.StatusListConversations? = GeneratedConversationsApi.StatusListConversations.ACTIVE,
        keyword: kotlin.String? = null,
        groupFilter: GeneratedConversationsApi.GroupFilterListConversations? = GeneratedConversationsApi.GroupFilterListConversations.ALL,
        groupId: kotlin.Long? = null,
        requestOptions: RequestOptions,
    ): ConversationSummaryList =
        userClient.execute(requestOptions) { api -> api.conversations.listConversations(channelId, current, size, status, keyword, groupFilter, groupId).bodyOrThrow() }

    /**
     * 查询活动会话 / List active conversations
     *
     * @return 契约定义的强类型响应。 / The typed response defined by the contract.
     */
    public suspend fun listActiveConversations(
    ): ConversationIds = listActiveConversations(RequestOptions())
    public suspend fun listActiveConversations(
        requestOptions: RequestOptions,
    ): ConversationIds =
        userClient.execute(requestOptions) { api -> api.conversations.listActiveConversations(channelId).bodyOrThrow() }

    /**
     * 查询未读会话 / List unread conversations
     *
     * @return 契约定义的强类型响应。 / The typed response defined by the contract.
     */
    public suspend fun listUnreadConversations(
    ): ConversationIds = listUnreadConversations(RequestOptions())
    public suspend fun listUnreadConversations(
        requestOptions: RequestOptions,
    ): ConversationIds =
        userClient.execute(requestOptions) { api -> api.conversations.listUnreadConversations(channelId).bodyOrThrow() }

    /**
     * 批量查询会话活动 / Query conversation activities
     *
     * @param input 批量查询会话活动 / Query conversation activities 的强类型请求体。 / Typed request body for queryConversationActivities.
     * @return 契约定义的强类型响应。 / The typed response defined by the contract.
     */
    public suspend fun queryConversationActivities(
        input: ConversationActivityBatchInput,
    ): ConversationActivityList = queryConversationActivities(input, RequestOptions())
    public suspend fun queryConversationActivities(
        input: ConversationActivityBatchInput,
        requestOptions: RequestOptions,
    ): ConversationActivityList =
        userClient.execute(requestOptions) { api -> api.conversations.queryConversationActivities(channelId, input).bodyOrThrow() }

    /**
     * 推进会话已读游标 / Mark a conversation as read
     *
     * @param conversationId 会话 ID；必须属于当前外部用户。 / Conversation ID owned by the current external user.
     * @param input 推进会话已读游标 / Mark a conversation as read 的强类型请求体。 / Typed request body for markConversationRead.
     * @return 契约定义的强类型响应。 / The typed response defined by the contract.
     */
    public suspend fun markConversationRead(
        conversationId: kotlin.String,
        input: ConversationReadReceiptInput,
    ): ConversationReadReceipt = markConversationRead(conversationId, input, RequestOptions())
    public suspend fun markConversationRead(
        conversationId: kotlin.String,
        input: ConversationReadReceiptInput,
        requestOptions: RequestOptions,
    ): ConversationReadReceipt =
        userClient.execute(requestOptions) { api -> api.conversations.markConversationRead(channelId, conversationId, input).bodyOrThrow() }

    /**
     * 读取会话统计 / Get conversation statistics
     *
     * @return 契约定义的强类型响应。 / The typed response defined by the contract.
     */
    public suspend fun getConversationStats(
    ): ConversationStats = getConversationStats(RequestOptions())
    public suspend fun getConversationStats(
        requestOptions: RequestOptions,
    ): ConversationStats =
        userClient.execute(requestOptions) { api -> api.conversations.getConversationStats(channelId).bodyOrThrow() }

    /**
     * 读取会话标题 / Get conversation title
     *
     * @param conversationId 会话 ID；必须属于当前外部用户。 / Conversation ID owned by the current external user.
     * @return 契约定义的强类型响应。 / The typed response defined by the contract.
     */
    public suspend fun getConversationTitle(
        conversationId: kotlin.String,
    ): ConversationTitle = getConversationTitle(conversationId, RequestOptions())
    public suspend fun getConversationTitle(
        conversationId: kotlin.String,
        requestOptions: RequestOptions,
    ): ConversationTitle =
        userClient.execute(requestOptions) { api -> api.conversations.getConversationTitle(channelId, conversationId).bodyOrThrow() }

    /**
     * 更新会话标题 / Update conversation title
     *
     * @param conversationId 会话 ID；必须属于当前外部用户。 / Conversation ID owned by the current external user.
     * @param input 更新会话标题 / Update conversation title 的强类型请求体。 / Typed request body for updateConversationTitle.
     * @return 契约定义的强类型响应。 / The typed response defined by the contract.
     */
    public suspend fun updateConversationTitle(
        conversationId: kotlin.String,
        input: ConversationTitleInput,
    ): ConversationMetadata = updateConversationTitle(conversationId, input, RequestOptions())
    public suspend fun updateConversationTitle(
        conversationId: kotlin.String,
        input: ConversationTitleInput,
        requestOptions: RequestOptions,
    ): ConversationMetadata =
        userClient.execute(requestOptions) { api -> api.conversations.updateConversationTitle(channelId, conversationId, input).bodyOrThrow() }

    /**
     * 更新会话状态 / Update conversation status
     *
     * @param conversationId 会话 ID；必须属于当前外部用户。 / Conversation ID owned by the current external user.
     * @param input 更新会话状态 / Update conversation status 的强类型请求体。 / Typed request body for updateConversationStatus.
     * @return 契约定义的强类型响应。 / The typed response defined by the contract.
     */
    public suspend fun updateConversationStatus(
        conversationId: kotlin.String,
        input: ConversationStatusInput,
    ): Unit = updateConversationStatus(conversationId, input, RequestOptions())
    public suspend fun updateConversationStatus(
        conversationId: kotlin.String,
        input: ConversationStatusInput,
        requestOptions: RequestOptions,
    ): Unit =
        userClient.execute(requestOptions) { api -> api.conversations.updateConversationStatus(channelId, conversationId, input).bodyOrThrow() }

    /**
     * 查询会话分组 / List conversation groups
     *
     * @return 契约定义的强类型响应。 / The typed response defined by the contract.
     */
    public suspend fun listConversationGroups(
    ): ConversationGroupList = listConversationGroups(RequestOptions())
    public suspend fun listConversationGroups(
        requestOptions: RequestOptions,
    ): ConversationGroupList =
        userClient.execute(requestOptions) { api -> api.conversations.listConversationGroups(channelId).bodyOrThrow() }

    /**
     * 创建会话分组 / Create conversation group
     *
     * @param input 创建会话分组 / Create conversation group 的强类型请求体。 / Typed request body for createConversationGroup.
     * @return 契约定义的强类型响应。 / The typed response defined by the contract.
     */
    public suspend fun createConversationGroup(
        input: ConversationGroupNameInput,
    ): ConversationGroup = createConversationGroup(input, RequestOptions())
    public suspend fun createConversationGroup(
        input: ConversationGroupNameInput,
        requestOptions: RequestOptions,
    ): ConversationGroup =
        userClient.execute(requestOptions) { api -> api.conversations.createConversationGroup(channelId, input).bodyOrThrow() }

    /**
     * 重命名会话分组 / Rename conversation group
     *
     * @param groupId 字段 groupId / group id field。
     * @param input 重命名会话分组 / Rename conversation group 的强类型请求体。 / Typed request body for renameConversationGroup.
     * @return 契约定义的强类型响应。 / The typed response defined by the contract.
     */
    public suspend fun renameConversationGroup(
        groupId: kotlin.Long,
        input: ConversationGroupNameInput,
    ): ConversationGroup = renameConversationGroup(groupId, input, RequestOptions())
    public suspend fun renameConversationGroup(
        groupId: kotlin.Long,
        input: ConversationGroupNameInput,
        requestOptions: RequestOptions,
    ): ConversationGroup =
        userClient.execute(requestOptions) { api -> api.conversations.renameConversationGroup(channelId, groupId, input).bodyOrThrow() }

    /**
     * 删除会话分组 / Delete conversation group
     *
     * @param groupId 字段 groupId / group id field。
     * @return 契约定义的强类型响应。 / The typed response defined by the contract.
     */
    public suspend fun deleteConversationGroup(
        groupId: kotlin.Long,
    ): ConversationGroupDeleted = deleteConversationGroup(groupId, RequestOptions())
    public suspend fun deleteConversationGroup(
        groupId: kotlin.Long,
        requestOptions: RequestOptions,
    ): ConversationGroupDeleted =
        userClient.execute(requestOptions) { api -> api.conversations.deleteConversationGroup(channelId, groupId).bodyOrThrow() }

    /**
     * 移动会话分组 / Move conversation to group
     *
     * @param conversationId 会话 ID；必须属于当前外部用户。 / Conversation ID owned by the current external user.
     * @param input 移动会话分组 / Move conversation to group 的强类型请求体。 / Typed request body for moveConversationToGroup.
     * @return 契约定义的强类型响应。 / The typed response defined by the contract.
     */
    public suspend fun moveConversationToGroup(
        conversationId: kotlin.String,
        input: UpdateConversationGroupInput,
    ): ConversationMetadata = moveConversationToGroup(conversationId, input, RequestOptions())
    public suspend fun moveConversationToGroup(
        conversationId: kotlin.String,
        input: UpdateConversationGroupInput,
        requestOptions: RequestOptions,
    ): ConversationMetadata =
        userClient.execute(requestOptions) { api -> api.conversations.moveConversationToGroup(channelId, conversationId, input).bodyOrThrow() }

}

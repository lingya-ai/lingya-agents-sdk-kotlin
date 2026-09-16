package cloud.lingya.agents.sdk.api

import cloud.lingya.agents.sdk.AgentsUserClient
import cloud.lingya.agents.sdk.bodyOrThrow
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
    ): Unit =
        delegate.deleteConversation(channelId, conversationId).bodyOrThrow()

    /**
     * 读取上下文占用 / Get context usage
     *
     * @param conversationId 会话 ID；必须属于当前外部用户。 / Conversation ID owned by the current external user.
     * @return 契约定义的强类型响应。 / The typed response defined by the contract.
     */
    public suspend fun getConversationContextUsage(
        conversationId: kotlin.String,
    ): ConversationContextUsage =
        delegate.getConversationContextUsage(channelId, conversationId).bodyOrThrow()

    /**
     * 分页查询会话 / List conversations
     *
     * @param current 从 0 开始的页码。 / Zero-based page index.
     * @param size 单页记录数。 / Number of records per page.
     * @param orderBy 排序字段列表。 / Ordered list of sort fields.
     * @param orderDirection 排序方向。 / Sort direction.
     * @param orderNullHandling 空值排序策略。 / Null ordering strategy.
     * @param keyword 标题或正文检索关键字。 / Title or content search keyword.
     * @param status 状态过滤条件。 / Status filter.
     * @return 契约定义的强类型响应。 / The typed response defined by the contract.
     */
    public suspend fun listConversations(
        current: kotlin.Int? = null,
        size: kotlin.Int? = 30,
        orderBy: kotlin.collections.List<kotlin.String>? = null,
        orderDirection: GeneratedConversationsApi.OrderDirectionListConversations? = GeneratedConversationsApi.OrderDirectionListConversations.ASC,
        orderNullHandling: GeneratedConversationsApi.OrderNullHandlingListConversations? = GeneratedConversationsApi.OrderNullHandlingListConversations.NATIVE,
        keyword: kotlin.String? = null,
        status: kotlin.String? = null,
    ): ConversationSummaryList =
        delegate.listConversations(channelId, current, size, orderBy, orderDirection, orderNullHandling, keyword, status).bodyOrThrow()

    /**
     * 查询活动会话 / List active conversations
     *
     * @return 契约定义的强类型响应。 / The typed response defined by the contract.
     */
    public suspend fun listActiveConversations(
    ): ConversationIds =
        delegate.listActiveConversations(channelId).bodyOrThrow()

    /**
     * 查询未读会话 / List unread conversations
     *
     * @return 契约定义的强类型响应。 / The typed response defined by the contract.
     */
    public suspend fun listUnreadConversations(
    ): ConversationIds =
        delegate.listUnreadConversations(channelId).bodyOrThrow()

    /**
     * 批量查询会话活动 / Query conversation activities
     *
     * @param input 批量查询会话活动 / Query conversation activities 的强类型请求体。 / Typed request body for queryConversationActivities.
     * @return 契约定义的强类型响应。 / The typed response defined by the contract.
     */
    public suspend fun queryConversationActivities(
        input: ConversationActivityBatchInput,
    ): ConversationActivityList =
        delegate.queryConversationActivities(channelId, input).bodyOrThrow()

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
    ): ConversationReadReceipt =
        delegate.markConversationRead(channelId, conversationId, input).bodyOrThrow()

    /**
     * 读取会话统计 / Get conversation statistics
     *
     * @return 契约定义的强类型响应。 / The typed response defined by the contract.
     */
    public suspend fun getConversationStats(
    ): ConversationStats =
        delegate.getConversationStats(channelId).bodyOrThrow()

    /**
     * 读取会话标题 / Get conversation title
     *
     * @param conversationId 会话 ID；必须属于当前外部用户。 / Conversation ID owned by the current external user.
     * @return 契约定义的强类型响应。 / The typed response defined by the contract.
     */
    public suspend fun getConversationTitle(
        conversationId: kotlin.String,
    ): ConversationTitle =
        delegate.getConversationTitle(channelId, conversationId).bodyOrThrow()

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
    ): Unit =
        delegate.updateConversationTitle(channelId, conversationId, input).bodyOrThrow()

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
    ): Unit =
        delegate.updateConversationStatus(channelId, conversationId, input).bodyOrThrow()

    /**
     * 查询会话分享 / List conversation shares
     *
     * @param conversationId 会话 ID；必须属于当前外部用户。 / Conversation ID owned by the current external user.
     * @return 契约定义的强类型响应。 / The typed response defined by the contract.
     */
    public suspend fun listConversationShares(
        conversationId: kotlin.String,
    ): ConversationShareList =
        delegate.listConversationShares(channelId, conversationId).bodyOrThrow()

    /**
     * 创建会话分享 / Create a conversation share
     *
     * @param conversationId 会话 ID；必须属于当前外部用户。 / Conversation ID owned by the current external user.
     * @param input 创建会话分享 / Create a conversation share 的强类型请求体。 / Typed request body for createConversationShare.
     * @return 契约定义的强类型响应。 / The typed response defined by the contract.
     */
    public suspend fun createConversationShare(
        conversationId: kotlin.String,
        input: ConversationShareInput,
    ): ConversationShareCreated =
        delegate.createConversationShare(channelId, conversationId, input).bodyOrThrow()

    /**
     * 撤销会话分享 / Revoke a conversation share
     *
     * @param conversationId 会话 ID；必须属于当前外部用户。 / Conversation ID owned by the current external user.
     * @param shareId 会话分享记录 ID。 / Conversation-share record ID.
     * @return 契约定义的强类型响应。 / The typed response defined by the contract.
     */
    public suspend fun revokeConversationShare(
        conversationId: kotlin.String,
        shareId: kotlin.Long,
    ): ConversationShareRevoked =
        delegate.revokeConversationShare(channelId, conversationId, shareId).bodyOrThrow()

}

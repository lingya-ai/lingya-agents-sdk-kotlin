package cloud.lingya.agents.sdk.api

import cloud.lingya.agents.sdk.generated.api.ConversationsApi
import cloud.lingya.agents.sdk.generated.model.*
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking

/**
 * conversations 分组的 Java 友好阻塞接口。 / Java-friendly blocking conversations operations.
 *
 * @author 思追(shaco)
 */
public class BlockingLingyaConversationsApi internal constructor(
    private val delegate: LingyaConversationsApi,
) {
    /**
     * 删除会话 / Delete a conversation
     *
     * @param conversationId 会话 ID；必须属于当前外部用户。 / Conversation ID owned by the current external user.
     * @return 契约定义的强类型响应。 / The typed response defined by the contract.
     */
    public fun deleteConversation(
        conversationId: kotlin.String,
    ): Unit = runBlocking {
        delegate.deleteConversation(conversationId)
    }

    /**
     * 读取上下文占用 / Get context usage
     *
     * @param conversationId 会话 ID；必须属于当前外部用户。 / Conversation ID owned by the current external user.
     * @return 契约定义的强类型响应。 / The typed response defined by the contract.
     */
    public fun getConversationContextUsage(
        conversationId: kotlin.String,
    ): ConversationContextUsage = runBlocking {
        delegate.getConversationContextUsage(conversationId)
    }

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
    public fun listConversations(
        current: kotlin.Int? = null,
        size: kotlin.Int? = 30,
        orderBy: kotlin.collections.List<kotlin.String>? = null,
        orderDirection: ConversationsApi.OrderDirectionListConversations? = ConversationsApi.OrderDirectionListConversations.ASC,
        orderNullHandling: ConversationsApi.OrderNullHandlingListConversations? = ConversationsApi.OrderNullHandlingListConversations.NATIVE,
        keyword: kotlin.String? = null,
        status: kotlin.String? = null,
    ): ConversationSummaryList = runBlocking {
        delegate.listConversations(current, size, orderBy, orderDirection, orderNullHandling, keyword, status)
    }

    /**
     * 查询活动会话 / List active conversations
     *
     * @return 契约定义的强类型响应。 / The typed response defined by the contract.
     */
    public fun listActiveConversations(
    ): ConversationIds = runBlocking {
        delegate.listActiveConversations()
    }

    /**
     * 查询未读会话 / List unread conversations
     *
     * @return 契约定义的强类型响应。 / The typed response defined by the contract.
     */
    public fun listUnreadConversations(
    ): ConversationIds = runBlocking {
        delegate.listUnreadConversations()
    }

    /**
     * 批量查询会话活动 / Query conversation activities
     *
     * @param input 批量查询会话活动 / Query conversation activities 的强类型请求体。 / Typed request body for queryConversationActivities.
     * @return 契约定义的强类型响应。 / The typed response defined by the contract.
     */
    public fun queryConversationActivities(
        input: ConversationActivityBatchInput,
    ): ConversationActivityList = runBlocking {
        delegate.queryConversationActivities(input)
    }

    /**
     * 推进会话已读游标 / Mark a conversation as read
     *
     * @param conversationId 会话 ID；必须属于当前外部用户。 / Conversation ID owned by the current external user.
     * @param input 推进会话已读游标 / Mark a conversation as read 的强类型请求体。 / Typed request body for markConversationRead.
     * @return 契约定义的强类型响应。 / The typed response defined by the contract.
     */
    public fun markConversationRead(
        conversationId: kotlin.String,
        input: ConversationReadReceiptInput,
    ): ConversationReadReceipt = runBlocking {
        delegate.markConversationRead(conversationId, input)
    }

    /**
     * 读取会话统计 / Get conversation statistics
     *
     * @return 契约定义的强类型响应。 / The typed response defined by the contract.
     */
    public fun getConversationStats(
    ): ConversationStats = runBlocking {
        delegate.getConversationStats()
    }

    /**
     * 读取会话标题 / Get conversation title
     *
     * @param conversationId 会话 ID；必须属于当前外部用户。 / Conversation ID owned by the current external user.
     * @return 契约定义的强类型响应。 / The typed response defined by the contract.
     */
    public fun getConversationTitle(
        conversationId: kotlin.String,
    ): ConversationTitle = runBlocking {
        delegate.getConversationTitle(conversationId)
    }

    /**
     * 更新会话标题 / Update conversation title
     *
     * @param conversationId 会话 ID；必须属于当前外部用户。 / Conversation ID owned by the current external user.
     * @param input 更新会话标题 / Update conversation title 的强类型请求体。 / Typed request body for updateConversationTitle.
     * @return 契约定义的强类型响应。 / The typed response defined by the contract.
     */
    public fun updateConversationTitle(
        conversationId: kotlin.String,
        input: ConversationTitleInput,
    ): Unit = runBlocking {
        delegate.updateConversationTitle(conversationId, input)
    }

    /**
     * 更新会话状态 / Update conversation status
     *
     * @param conversationId 会话 ID；必须属于当前外部用户。 / Conversation ID owned by the current external user.
     * @param input 更新会话状态 / Update conversation status 的强类型请求体。 / Typed request body for updateConversationStatus.
     * @return 契约定义的强类型响应。 / The typed response defined by the contract.
     */
    public fun updateConversationStatus(
        conversationId: kotlin.String,
        input: ConversationStatusInput,
    ): Unit = runBlocking {
        delegate.updateConversationStatus(conversationId, input)
    }

    /**
     * 查询会话分享 / List conversation shares
     *
     * @param conversationId 会话 ID；必须属于当前外部用户。 / Conversation ID owned by the current external user.
     * @return 契约定义的强类型响应。 / The typed response defined by the contract.
     */
    public fun listConversationShares(
        conversationId: kotlin.String,
    ): ConversationShareList = runBlocking {
        delegate.listConversationShares(conversationId)
    }

    /**
     * 创建会话分享 / Create a conversation share
     *
     * @param conversationId 会话 ID；必须属于当前外部用户。 / Conversation ID owned by the current external user.
     * @param input 创建会话分享 / Create a conversation share 的强类型请求体。 / Typed request body for createConversationShare.
     * @return 契约定义的强类型响应。 / The typed response defined by the contract.
     */
    public fun createConversationShare(
        conversationId: kotlin.String,
        input: ConversationShareInput,
    ): ConversationShareCreated = runBlocking {
        delegate.createConversationShare(conversationId, input)
    }

    /**
     * 撤销会话分享 / Revoke a conversation share
     *
     * @param conversationId 会话 ID；必须属于当前外部用户。 / Conversation ID owned by the current external user.
     * @param shareId 会话分享记录 ID。 / Conversation-share record ID.
     * @return 契约定义的强类型响应。 / The typed response defined by the contract.
     */
    public fun revokeConversationShare(
        conversationId: kotlin.String,
        shareId: kotlin.Long,
    ): ConversationShareRevoked = runBlocking {
        delegate.revokeConversationShare(conversationId, shareId)
    }

}

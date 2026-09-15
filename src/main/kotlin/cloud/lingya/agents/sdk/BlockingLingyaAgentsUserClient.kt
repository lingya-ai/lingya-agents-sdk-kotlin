package cloud.lingya.agents.sdk

import cloud.lingya.agents.sdk.event.AiChatBriefEvent
import cloud.lingya.agents.sdk.generated.model.AiChatInput
import cloud.lingya.agents.sdk.generated.model.AiChatStreamInput
import cloud.lingya.agents.sdk.generated.model.AiChatSubmission
import cloud.lingya.agents.sdk.api.BlockingLingyaChatApi
import cloud.lingya.agents.sdk.api.BlockingLingyaConfigurationApi
import cloud.lingya.agents.sdk.api.BlockingLingyaConversationsApi
import cloud.lingya.agents.sdk.api.BlockingLingyaEventsApi
import cloud.lingya.agents.sdk.api.BlockingLingyaFilesApi
import cloud.lingya.agents.sdk.api.BlockingLingyaInteractionsApi
import cloud.lingya.agents.sdk.api.BlockingLingyaKnowledgeApi
import cloud.lingya.agents.sdk.api.BlockingLingyaMessagesApi
import cloud.lingya.agents.sdk.api.BlockingLingyaSqlApi
import cloud.lingya.agents.sdk.api.BlockingLingyaWorkspaceApi

/** Java-friendly blocking facade for common calls. */
public class BlockingLingyaAgentsUserClient internal constructor(
    private val delegate: LingyaAgentsUserClient,
) {
    /** 创建聊天和消费实时事件。 / Creates chats and consumes live events. */
    public val chat: BlockingLingyaChatApi = BlockingLingyaChatApi(delegate.chat)

    /** 读取 Agent 和会话配置。 / Reads Agent and conversation configuration. */
    public val configuration: BlockingLingyaConfigurationApi = BlockingLingyaConfigurationApi(delegate.configuration)

    /** 管理会话、状态和分享。 / Manages conversations, state, and sharing. */
    public val conversations: BlockingLingyaConversationsApi = BlockingLingyaConversationsApi(delegate.conversations)

    /** 读取持久化聊天事件。 / Reads persisted chat events. */
    public val events: BlockingLingyaEventsApi = BlockingLingyaEventsApi(delegate.events)

    /** 管理文件和预签名地址。 / Manages files and presigned URLs. */
    public val files: BlockingLingyaFilesApi = BlockingLingyaFilesApi(delegate.files)

    /** 处理计划审批和用户回答。 / Handles plan approvals and user answers. */
    public val interactions: BlockingLingyaInteractionsApi = BlockingLingyaInteractionsApi(delegate.interactions)

    /** 读取知识引用元数据。 / Reads knowledge citation metadata. */
    public val knowledge: BlockingLingyaKnowledgeApi = BlockingLingyaKnowledgeApi(delegate.knowledge)

    /** 读取消息和异步任务。 / Reads messages and asynchronous tasks. */
    public val messages: BlockingLingyaMessagesApi = BlockingLingyaMessagesApi(delegate.messages)

    /** 查询和导出 SQL 结果。 / Reads and exports SQL results. */
    public val sql: BlockingLingyaSqlApi = BlockingLingyaSqlApi(delegate.sql)

    /** 浏览会话工作区制品。 / Browses conversation workspace artifacts. */
    public val workspace: BlockingLingyaWorkspaceApi = BlockingLingyaWorkspaceApi(delegate.workspace)

    /** @suppress */
    @Deprecated("Use chat.createChat(input)", ReplaceWith("chat.createChat(input)"))
    public fun createChat(input: AiChatInput): AiChatSubmission = chat.createChat(input)

    /** @suppress */
    @Deprecated("Use chat.continueChat(conversationId, input)", ReplaceWith("chat.continueChat(conversationId, input)"))
    public fun continueChat(conversationId: String, input: AiChatInput): AiChatSubmission =
        chat.continueChat(conversationId, input)

    /** @suppress */
    @Deprecated("Use chat.streamChatEvents(conversationId, AiChatStreamInput(messageId), requestId)")
    @JvmOverloads
    public fun collectChatEvents(
        conversationId: String,
        messageId: String,
        requestId: String? = null,
    ): List<AiChatBriefEvent> = chat.streamChatEvents(conversationId, AiChatStreamInput(messageId), requestId)

    /** @suppress */
    @Deprecated("Use the channel-bound groups; low-level APIs remain available on the coroutine client")
    public fun getApis(): LingyaAgentsApis = delegate.lowLevel
}

package cloud.lingya.agents.sdk

import cloud.lingya.agents.sdk.event.AiChatBriefEvent
import cloud.lingya.agents.sdk.event.AiChatEventDecoder
import cloud.lingya.agents.sdk.event.SseEventParser
import cloud.lingya.agents.sdk.api.ChatApi
import cloud.lingya.agents.sdk.api.ConfigurationApi
import cloud.lingya.agents.sdk.api.ConversationsApi
import cloud.lingya.agents.sdk.api.EventsApi
import cloud.lingya.agents.sdk.api.FilesApi
import cloud.lingya.agents.sdk.api.InteractionsApi
import cloud.lingya.agents.sdk.api.KnowledgeApi
import cloud.lingya.agents.sdk.api.MessagesApi
import cloud.lingya.agents.sdk.api.SqlApi
import cloud.lingya.agents.sdk.api.WorkspaceApi
import cloud.lingya.agents.sdk.generated.model.AiChatInput
import cloud.lingya.agents.sdk.generated.model.AiChatStreamInput
import cloud.lingya.agents.sdk.generated.model.AiChatSubmission
import cloud.lingya.agents.sdk.generated.model.ChatStreamProbeEvent
import cloud.lingya.agents.sdk.generated.model.ChatStreamProbeInput
import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.map
import okhttp3.Call
import okhttp3.Callback
import okhttp3.HttpUrl
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.io.IOException

/**
 * 绑定一个 channel 和外部用户的签名协程客户端。 / Signed coroutine client scoped to one channel and external user.
 *
 * 业务方法从本客户端读取 `channelId`，调用者不应在每次请求中重复提供。
 * / Business operations read `channelId` from this client instead of accepting it per request.
 *
 * @author 思追(shaco)
 */
public class AgentsUserClient internal constructor(
    private val baseUrl: HttpUrl,
    public val channelId: String,
    private val transport: OkHttpClient,
    private val objectMapper: ObjectMapper,
) {
    /** 供迁移和高级协议调试使用的生成 API。 / Generated APIs for migration and protocol-level debugging. */
    public val lowLevel: AgentsApis = AgentsApis(
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(transport)
            .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            .build(),
    )

    /** @suppress */
    @Deprecated("Use lowLevel only for compatibility; prefer the channel-bound groups")
    public val apis: AgentsApis
        get() = lowLevel

    /** 创建聊天和消费实时事件。 / Creates chats and consumes live events. */
    public val chat: ChatApi = ChatApi(channelId, lowLevel.chat, this)

    /** 读取 Agent 和会话配置。 / Reads Agent and conversation configuration. */
    public val configuration: ConfigurationApi = ConfigurationApi(channelId, lowLevel.configuration, this)

    /** 管理会话、状态和分享。 / Manages conversations, state, and sharing. */
    public val conversations: ConversationsApi = ConversationsApi(channelId, lowLevel.conversations, this)

    /** 读取持久化聊天事件。 / Reads persisted chat events. */
    public val events: EventsApi = EventsApi(channelId, lowLevel.events, this)

    /** 管理文件和预签名地址。 / Manages files and presigned URLs. */
    public val files: FilesApi = FilesApi(channelId, lowLevel.files, this)

    /** 处理计划审批和用户回答。 / Handles plan approvals and user answers. */
    public val interactions: InteractionsApi = InteractionsApi(channelId, lowLevel.interactions, this)

    /** 读取知识引用元数据。 / Reads knowledge citation metadata. */
    public val knowledge: KnowledgeApi = KnowledgeApi(channelId, lowLevel.knowledge, this)

    /** 读取消息和异步任务。 / Reads messages and asynchronous tasks. */
    public val messages: MessagesApi = MessagesApi(channelId, lowLevel.messages, this)

    /** 查询和导出 SQL 结果。 / Reads and exports SQL results. */
    public val sql: SqlApi = SqlApi(channelId, lowLevel.sql, this)

    /** 浏览会话工作区制品。 / Browses conversation workspace artifacts. */
    public val workspace: WorkspaceApi = WorkspaceApi(channelId, lowLevel.workspace, this)

    /** @suppress */
    @Deprecated("Use chat.createChat(input)", ReplaceWith("chat.createChat(input)"))
    public suspend fun createChat(input: AiChatInput): AiChatSubmission =
        chat.createChat(input)

    /** @suppress */
    @Deprecated("Use chat.continueChat(conversationId, input)", ReplaceWith("chat.continueChat(conversationId, input)"))
    public suspend fun continueChat(conversationId: String, input: AiChatInput): AiChatSubmission =
        chat.continueChat(conversationId, input)

    /** @suppress */
    @Deprecated("Use chat.streamChatEvents(conversationId, AiChatStreamInput(messageId), requestId)")
    @JvmOverloads
    public fun streamChatEvents(
        conversationId: String,
        messageId: String,
        requestId: String? = null,
    ): Flow<AiChatBriefEvent> = chat.streamChatEvents(conversationId, AiChatStreamInput(messageId), requestId)

    internal fun streamChatEventsInternal(
        conversationId: String,
        input: AiChatStreamInput,
        requestId: String? = null,
    ): Flow<AiChatBriefEvent> {
        val url = baseUrl.newBuilder()
            .addPathSegments("api/agents/channel/openapi/v1")
            .addPathSegment(channelId)
            .addPathSegments("chat/conversations")
            .addPathSegment(conversationId)
            .addPathSegment("stream")
            .build()
        val bodyBytes = objectMapper.writeValueAsBytes(input)
        val request = Request.Builder()
            .url(url)
            .post(bodyBytes.toRequestBody(JSON_MEDIA_TYPE))
            .header("Accept", "text/event-stream")
            .apply { if (requestId != null) header("X-Request-ID", requestId) }
            .build()
        val decoder = AiChatEventDecoder(objectMapper)
        return streamData(request).map(decoder::decode)
    }

    /** @suppress */
    @Deprecated("Use chat.probeEventStream(ChatStreamProbeInput(probeId), requestId)")
    @JvmOverloads
    public fun probeEventStream(
        probeId: String,
        requestId: String? = null,
    ): Flow<ChatStreamProbeEvent> = chat.probeEventStream(ChatStreamProbeInput(probeId), requestId)

    internal fun probeEventStreamInternal(
        input: ChatStreamProbeInput,
        requestId: String? = null,
    ): Flow<ChatStreamProbeEvent> {
        val url = baseUrl.newBuilder()
            .addPathSegments("api/agents/channel/openapi/v1")
            .addPathSegment(channelId)
            .addPathSegments("chat/stream-probe")
            .build()
        val bodyBytes = objectMapper.writeValueAsBytes(input)
        val request = Request.Builder()
            .url(url)
            .post(bodyBytes.toRequestBody(JSON_MEDIA_TYPE))
            .header("Accept", "text/event-stream")
            .apply { if (requestId != null) header("X-Request-ID", requestId) }
            .build()
        return streamData(request).map { objectMapper.readValue(it, ChatStreamProbeEvent::class.java) }
    }

    private fun streamData(request: Request): Flow<String> = callbackFlow {
        val call = transport.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                if (!call.isCanceled()) close(e)
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) {
                        close(ApiException(response.code, response.body.string()))
                        return
                    }
                    try {
                        val parser = SseEventParser()
                        val source = response.body.source()
                        while (!source.exhausted()) {
                            parser.accept(source.readUtf8Line() ?: break)?.let { trySendBlocking(it).getOrThrow() }
                        }
                        parser.finish()?.let { trySendBlocking(it).getOrThrow() }
                        close()
                    } catch (exception: Exception) {
                        if (!call.isCanceled()) close(exception)
                    }
                }
            }
        })
        awaitClose { call.cancel() }
    }

    private companion object {
        val JSON_MEDIA_TYPE = "application/json".toMediaType()
    }
}

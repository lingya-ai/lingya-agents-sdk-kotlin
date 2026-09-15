package cloud.lingya.agents.sdk

import cloud.lingya.agents.sdk.event.AiChatBriefEvent
import cloud.lingya.agents.sdk.event.AiChatEventDecoder
import cloud.lingya.agents.sdk.event.SseEventParser
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

/** Signed coroutine client scoped to one channel and one external user. */
public class LingyaAgentsUserClient internal constructor(
    private val baseUrl: HttpUrl,
    public val channelId: String,
    private val transport: OkHttpClient,
    private val objectMapper: ObjectMapper,
) {
    /** All generated endpoint groups. */
    public val apis: LingyaAgentsApis = LingyaAgentsApis(
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(transport)
            .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            .build(),
    )

    /** Starts a new chat. */
    public suspend fun createChat(input: AiChatInput): AiChatSubmission =
        apis.chat.createChat(channelId, input).bodyOrThrow()

    /** Continues an existing conversation. */
    public suspend fun continueChat(conversationId: String, input: AiChatInput): AiChatSubmission =
        apis.chat.continueChat(channelId, conversationId, input).bodyOrThrow()

    /** Streams chat events while preserving unknown event JSON. */
    @JvmOverloads
    public fun streamChatEvents(
        conversationId: String,
        messageId: String,
        requestId: String? = null,
    ): Flow<AiChatBriefEvent> {
        val url = baseUrl.newBuilder()
            .addPathSegments("api/agents/channel/openapi/v1")
            .addPathSegment(channelId)
            .addPathSegments("chat/conversations")
            .addPathSegment(conversationId)
            .addPathSegment("stream")
            .build()
        val bodyBytes = objectMapper.writeValueAsBytes(AiChatStreamInput(messageId))
        val request = Request.Builder()
            .url(url)
            .post(bodyBytes.toRequestBody(JSON_MEDIA_TYPE))
            .header("Accept", "text/event-stream")
            .apply { if (requestId != null) header("X-Request-ID", requestId) }
            .build()
        val decoder = AiChatEventDecoder(objectMapper)
        return streamData(request).map(decoder::decode)
    }

    /** Opens the diagnostic SSE probe endpoint. */
    @JvmOverloads
    public fun probeEventStream(
        probeId: String,
        requestId: String? = null,
    ): Flow<ChatStreamProbeEvent> {
        val url = baseUrl.newBuilder()
            .addPathSegments("api/agents/channel/openapi/v1")
            .addPathSegment(channelId)
            .addPathSegments("chat/stream-probe")
            .build()
        val bodyBytes = objectMapper.writeValueAsBytes(ChatStreamProbeInput(probeId))
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
                        close(LingyaApiException(response.code, response.body.string()))
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

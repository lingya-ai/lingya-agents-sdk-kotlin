package cloud.lingya.agents.sdk

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import java.time.Clock

/** Entry point for a trusted server-side Lingya Agents integration. */
public open class AgentsClient @JvmOverloads constructor(
    baseUrl: String,
    public val channelId: String,
    private val credentials: OpenApiCredentials,
    private val transport: OkHttpClient = OkHttpClient.Builder().build(),
    private val objectMapper: ObjectMapper = defaultObjectMapper(),
    private val retryPolicy: RetryPolicy = RetryPolicy.NONE,
    private val clock: Clock = Clock.systemUTC(),
    private val nonceSource: NonceSource = SecureNonceSource(),
) {
    internal val baseUrl: HttpUrl = normalizeBaseUrl(baseUrl)

    init {
        require(channelId.isNotBlank()) { "channelId must not be blank" }
    }

    /** Creates a client that signs every call with one external user identity. */
    public fun forUser(externalUserId: String): AgentsUserClient {
        val builder = transport.newBuilder().retryOnConnectionFailure(false)
        if (retryPolicy.maxAttempts > 1) builder.addInterceptor(IdempotentRetryInterceptor(retryPolicy))
        builder.addNetworkInterceptor(OpenApiSigningInterceptor(credentials, externalUserId, clock, nonceSource))
        return AgentsUserClient(baseUrl, channelId, builder.build(), objectMapper)
    }

    /** Returns the Java-friendly blocking facade for one external user. */
    public fun blockingForUser(externalUserId: String): BlockingAgentsUserClient =
        BlockingAgentsUserClient(forUser(externalUserId))

    private companion object {
        fun normalizeBaseUrl(value: String): HttpUrl = (if (value.endsWith('/')) value else "$value/").toHttpUrl()

        fun defaultObjectMapper(): ObjectMapper = jacksonObjectMapper()
            .findAndRegisterModules()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    }
}

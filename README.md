# Lingya Agents SDK for Kotlin/JVM

Server-side Kotlin/JVM SDK for the 46 signed endpoints in the [Lingya Agents OpenAPI](https://github.com/lingya-ai/lingya-agents-openapi). It targets JVM 17, uses coroutines for ordinary calls, and exposes SSE as `Flow<AiChatBriefEvent>`.

> Keep the channel secret on trusted servers. Never embed it in a browser, mobile app, desktop app, or other distributed client.

## Install

```kotlin
dependencies {
    implementation("ai.lingya:lingya-agents-sdk:0.1.0")
}
```

The SDK uses Maven Central. For builds in mainland China, this repository already resolves plugins and dependencies from Aliyun first. The Gradle Wrapper downloads Gradle 9.6.1 from Tencent Cloud; change `distributionUrl` back to `services.gradle.org` if your environment cannot reach that mirror.

## Kotlin

```kotlin
import ai.lingya.agents.sdk.LingyaAgentsClient
import ai.lingya.agents.sdk.OpenApiCredentials
import ai.lingya.agents.sdk.generated.model.AiChatInput

val client = LingyaAgentsClient(
    baseUrl = "https://tenant.example.com",
    channelId = System.getenv("LINGYA_CHANNEL_ID"),
    credentials = OpenApiCredentials(
        System.getenv("LINGYA_ACCESS_KEY"),
        System.getenv("LINGYA_ACCESS_SECRET"),
    ),
)
val user = client.forUser("your-stable-external-user-id")
val submission = user.createChat(AiChatInput(query = "你好"))
```

All generated groups are available through `user.apis`, for example `user.apis.conversations.listConversations(...)`. Generated methods return Retrofit `Response<T>`; call `bodyOrThrow()` to map non-2xx responses to `LingyaApiException`.

## SSE

```kotlin
user.streamChatEvents(submission.conversationId, submission.messageId).collect { event ->
    println("${event.type}: ${event.raw}")
}
```

Unknown event types retain their original Jackson `JsonNode`, so a newer server can extend the stream without losing data.

## Java

```java
var client = new LingyaAgentsClient(
    "https://tenant.example.com", channelId,
    new OpenApiCredentials(accessKey, secret)
);
var user = client.blockingForUser("external-user-id");
var result = user.createChat(new AiChatInput("Hello", null, null, null));
var events = user.collectChatEvents(result.getConversationId(), result.getMessageId());
```

## File upload

```kotlin
val upload = user.apis.files.createPreSignedUpload(
    channelId,
    GeneratePreSignedUrlInput("report.pdf", "conversation", md5),
).bodyOrThrow()

// Upload bytes directly to upload.url with the returned headers using an unsigned HTTP client.

val file = user.apis.files.confirmPreSignedUpload(
    channelId,
    ConfirmUploadInput(requireNotNull(upload.fileUk), md5),
).bodyOrThrow()
```

## Custom transport and retries

```kotlin
val transport = OkHttpClient.Builder()
    .callTimeout(Duration.ofSeconds(90))
    .build()

val client = LingyaAgentsClient(
    baseUrl,
    channelId,
    OpenApiCredentials(accessKey, secret),
    transport = transport,
    retryPolicy = RetryPolicy(maxAttempts = 3),
)
```

Retries are off by default. When enabled, the SDK retries only `GET` and `HEAD`; writes are never retried. Each attempt gets a new nonce and signature after OkHttp has fixed the final path, query, content type, and body bytes.

See [README.zh-CN.md](README.zh-CN.md) for Chinese documentation.

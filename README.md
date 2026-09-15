# 灵涯 Agents Kotlin/JVM SDK
Lingya Agents SDK for Kotlin/JVM

这是面向服务端的 Kotlin/JVM SDK，覆盖 [灵涯 Agents OpenAPI](https://github.com/lingya-ai/lingya-agents-openapi) 中 46 个签名接口；目标字节码为 JVM 17，普通调用使用协程，SSE 返回 `Flow<AiChatBriefEvent>`。
This server-side Kotlin/JVM SDK covers the 46 signed operations in [Lingya Agents OpenAPI](https://github.com/lingya-ai/lingya-agents-openapi). It targets JVM 17, uses coroutines for ordinary calls, and exposes SSE as `Flow<AiChatBriefEvent>`.

请只在可信服务端保存渠道 secret；切勿把它嵌入浏览器、移动端、桌面端或其他会分发给最终用户的客户端。
Keep the channel secret on trusted servers only. Never embed it in browsers, mobile apps, desktop apps, or other clients distributed to end users.

## 安装
Installation

```kotlin
dependencies {
    implementation("ai.lingya:lingya-agents-sdk:0.1.0")
}
```

SDK 使用 Maven Central；中国大陆构建默认优先使用阿里云依赖与插件镜像，Gradle Wrapper 从腾讯云下载 Gradle 9.6.1。
The SDK uses Maven Central. Builds in mainland China prefer Aliyun dependency and plugin mirrors, and the Gradle Wrapper downloads Gradle 9.6.1 from Tencent Cloud.

若当前网络无法访问腾讯云镜像，可把 `distributionUrl` 改回 `services.gradle.org`。
If your network cannot reach the Tencent Cloud mirror, change `distributionUrl` back to `services.gradle.org`.

## Kotlin 调用
Kotlin usage

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

全部生成的 API 分组都可通过 `user.apis` 访问，例如 `user.apis.conversations.listConversations(...)`。
All generated API groups are available through `user.apis`, for example `user.apis.conversations.listConversations(...)`.

生成方法返回 Retrofit `Response<T>`；调用 `bodyOrThrow()` 可把非 2xx 响应转换为 `LingyaApiException`。
Generated methods return Retrofit `Response<T>`; call `bodyOrThrow()` to convert non-2xx responses into `LingyaApiException`.

## 服务端推送事件（SSE）
Server-sent events (SSE)

```kotlin
user.streamChatEvents(submission.conversationId, submission.messageId).collect { event ->
    when (event) {
        is AiChatBriefEvent.Message -> println(event.value.message)
        is AiChatBriefEvent.Unknown -> println("Unknown ${event.type}: ${event.rawJson}")
        else -> println(event.type)
    }
}
```

每种已发布响应都有明确的 data class；事件、工具扩展、模型消息与 SQL 图表列使用密封类型，生产 API 不暴露 `JsonNode`、`Map` 或动态 `Any`。
Every published response has an explicit data class. Events, tool extensions, model messages, and SQL chart columns use sealed types; production APIs expose no `JsonNode`, `Map`, or dynamic `Any`.

只有未来新增的判别值会进入对应的 `Unknown.rawJson: String` 分支，原始 JSON 以字符串形式完整保留。
Only future discriminator values enter the corresponding `Unknown.rawJson: String` branch, which preserves the complete original JSON as a string.

## Java 调用
Java usage

```java
var client = new LingyaAgentsClient(
    "https://tenant.example.com", channelId,
    new OpenApiCredentials(accessKey, secret)
);
var user = client.blockingForUser("external-user-id");
var result = user.createChat(new AiChatInput("Hello", null, null, null));
var events = user.collectChatEvents(result.getConversationId(), result.getMessageId());
```

## 文件上传
File upload

```kotlin
val upload = user.apis.files.createPreSignedUpload(
    channelId,
    GeneratePreSignedUrlInput("report.pdf", "ai-chat-attachments", md5),
).bodyOrThrow()

// 使用无签名 HTTP 客户端，按照返回的 headers 把文件字节直接上传到 upload.url。
// Use an unsigned HTTP client to upload the file bytes directly to upload.url with the returned headers.

val file = user.apis.files.confirmPreSignedUpload(
    channelId,
    ConfirmUploadInput(requireNotNull(upload.fileUk), md5),
).bodyOrThrow()
```

## 自定义传输与重试
Custom transport and retries

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

默认关闭重试；启用后也只重试 `GET` 与 `HEAD`，不会重试写操作。
Retries are disabled by default. When enabled, only `GET` and `HEAD` are retried; write operations are never retried.

OkHttp 确定最终 path、query、content type 与正文（body）字节后，每次尝试都会生成新的 nonce 与签名。
After OkHttp fixes the final path, query, content type, and body bytes, every attempt receives a fresh nonce and signature.

## 真实接口验证
Live API verification

可选集成测试会逐一调用 46 个已发布 method/path，并将实际执行集合与内置契约进行比较。
The opt-in integration suite invokes every one of the 46 published method/path pairs and compares the executed set with the bundled contract.

有资源支持的接口校验成功响应；需要当前环境不具备的 SQL 结果、计划文件、引用、排队任务或待回答问题的接口，则校验明确的领域级 4xx 响应。
Resource-backed operations verify successful responses. Operations requiring unavailable SQL results, plan files, citations, queued tasks, or pending questions verify explicit domain-level 4xx responses.

测试还会验证诊断 SSE 流与真实对话生命周期，并在 `finally` 中删除专用测试会话；凭证只从进程环境读取，不会被测试持久化。
The suite also verifies the diagnostic SSE stream and a real chat lifecycle, deletes dedicated test conversations in `finally`, and reads credentials only from the process environment without persisting them.

```powershell
$env:LINGYA_LIVE_BASE_URL = "https://tenant.example.com/"
$env:LINGYA_LIVE_CHANNEL_ID = "your-channel-id"
$env:OPENAPI_AK = "your-access-key"
$env:OPENAPI_SK = "your-access-secret"
$env:LINGYA_LIVE_EXTERNAL_USER_ID = "stable-test-user"
./gradlew liveTest
```

未设置这些环境变量时会跳过真实测试，因此普通 pull request CI 不需要生产凭证；`liveTest` 任务始终重新执行，避免复用目标环境或凭证已经变化的缓存结果。
Without these variables, live tests are skipped, so ordinary pull request CI never requires production credentials. The `liveTest` task always executes to avoid reusing cached results after the target environment or credentials change.

如需独立的中文文档，可参阅 [README.zh-CN.md](README.zh-CN.md)。
For the standalone Chinese documentation, see [README.zh-CN.md](README.zh-CN.md).

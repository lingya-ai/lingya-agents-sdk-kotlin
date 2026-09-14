# Lingya Agents Kotlin/JVM SDK

这是 Lingya Agents 公共 HMAC API 的服务端 SDK，覆盖契约中的 46 个端点。普通请求使用 Kotlin 协程，SSE 返回 `Flow<AiChatBriefEvent>`，同时提供 Java 阻塞门面。

安全边界：SDK 只能运行在可信服务端。不得把 channel secret 放入浏览器、移动端、桌面端或其他会分发给用户的应用。

## 引入依赖

```kotlin
dependencies {
    implementation("ai.lingya:lingya-agents-sdk:0.1.0")
}
```

本仓库默认优先使用阿里云 Maven/Gradle Plugin 镜像，Gradle Wrapper 使用腾讯云镜像，官方仓库保留为故障兜底。

## 使用

```kotlin
val client = LingyaAgentsClient(
    baseUrl = "https://tenant.example.com",
    channelId = System.getenv("LINGYA_CHANNEL_ID"),
    credentials = OpenApiCredentials(
        System.getenv("LINGYA_ACCESS_KEY"),
        System.getenv("LINGYA_ACCESS_SECRET"),
    ),
)

val user = client.forUser("稳定的外部用户 ID")
val submission = user.createChat(AiChatInput(query = "你好"))

user.streamChatEvents(submission.conversationId, submission.messageId).collect { event ->
    println("${event.type}: ${event.raw}")
}
```

`user.apis` 暴露契约生成的全部接口分组；普通生成接口返回 Retrofit `Response<T>`，可以使用 `bodyOrThrow()` 统一处理失败响应。文件上传需要先调用 `createPreSignedUpload`，用独立、无 HMAC 的 HTTP 客户端向预签名 URL 上传原始字节，再调用 `confirmPreSignedUpload` 确认。

默认不重试。配置 `RetryPolicy(maxAttempts = 3)` 后也只重试 `GET`/`HEAD`，写操作仍不重试；每次尝试都会重新生成 nonce 和签名。

完整 Kotlin、Java、SSE、文件上传和自定义 transport 示例见 [英文 README](README.md)。

# Lingya Agents Kotlin/JVM SDK

这是 Lingya Agents 公共 HMAC API 的服务端 SDK，覆盖契约中的 46 个端点。普通请求使用 Kotlin 协程，SSE 返回 `Flow<AiChatBriefEvent>`，同时提供 Java 阻塞门面。

安全边界：SDK 只能运行在可信服务端。不得把 channel secret 放入浏览器、移动端、桌面端或其他会分发给用户的应用。

## 引入依赖

```kotlin
dependencies {
    implementation("cloud.lingya:lingya-agents-sdk:0.3.0")
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
val submission = user.chat.createChat(AiChatInput(query = "你好"))

user.chat.streamChatEvents(
    submission.conversationId,
    AiChatStreamInput(submission.messageId),
).collect { event ->
    when (event) {
        is AiChatBriefEvent.Message -> println(event.value.message)
        is AiChatBriefEvent.Unknown -> println("未知事件 ${event.type}: ${event.rawJson}")
        else -> println(event.type)
    }
}
```

`user.chat` 等十个绑定分组覆盖契约生成的全部 46 个接口，业务方法不再接收 `channelId`；`user.lowLevel` 只作为迁移入口保留到 1.0。文件上传需要先调用 `user.files.createPreSignedUpload`，用独立、无 HMAC 的 HTTP 客户端向预签名 URL 上传原始字节，再调用 `user.files.confirmPreSignedUpload` 确认。

所有已发布响应均使用明确的数据类。事件、工具扩展、模型消息和 SQL 图表列使用密封类型；SDK 不在生产 API 中暴露 `JsonNode`、`Map` 或 `Any`。服务端未来新增判别值时，仅对应的 `Unknown` 分支通过 `rawJson: String` 保留完整 JSON，现有类型不会退化为动态对象。

默认不重试。配置 `RetryPolicy(maxAttempts = 3)` 后也只重试 `GET`/`HEAD`，写操作仍不重试；每次尝试都会重新生成 nonce 和签名。

## 真实接口验证

可选的集成测试会实际调用契约中的全部 46 个 method/path，并把执行集合与仓库内契约进行比对。有稳定前置资源的接口验证成功响应；依赖 SQL 结果、计划文件、知识引用、排队任务或待回答问题的接口验证明确的领域 4xx 响应。测试同时覆盖 SSE 探针和完整聊天生命周期，并在 `finally` 中删除专用测试会话；凭证只从进程环境读取，不会写入测试或仓库。

```powershell
$env:LINGYA_LIVE_BASE_URL = "https://tenant.example.com/"
$env:LINGYA_LIVE_CHANNEL_ID = "your-channel-id"
$env:OPENAPI_AK = "your-access-key"
$env:OPENAPI_SK = "your-access-secret"
$env:LINGYA_LIVE_EXTERNAL_USER_ID = "stable-test-user"
./gradlew liveTest
```

未设置这些环境变量时真实接口测试会跳过，因此普通 pull request CI 不需要生产凭证。专用 `liveTest` 任务始终重新执行，避免凭证或目标环境变化时复用旧缓存。

完整 Kotlin、Java、SSE、文件上传和自定义 transport 示例见 [英文 README](README.md)。

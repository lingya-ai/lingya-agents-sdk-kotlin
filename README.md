# 灵涯 Agents Kotlin/JVM SDK
Lingya Agents SDK for Kotlin/JVM

用于在可信服务端调用灵涯 Agents OpenAPI。
Use this SDK to call Lingya Agents OpenAPI from a trusted server.

## 安装
Installation

```kotlin
dependencies {
    implementation("cloud.lingya:lingya-agents-sdk:1.0.0")
}
```

运行环境需要 JDK 17 或更高版本。
The runtime requires JDK 17 or later.

## 一次性令牌回调

选项构造接受挂起回调，每次请求前获取一枚新 token；原有 AK/SK 构造加 forUser 保持兼容。
Options construction accepts a suspending fresh-token callback. Legacy credential constructors remain supported.

```kotlin
val client = AgentsClient(AgentsClientOptions(
    baseUrl = baseUrl,
    channelId = channelId,
    auth = AgentsAuth.Token { context ->
        issueFreshToken(context.channelId) // 自己的业务服务接口，返回新 token
    },
))
val config = client.configuration.getAgentsConfig()
val blocking = client.blocking() // Java / 同步调用入口
```

HMAC 选项使用 `AgentsAuth.Hmac(credentials, externalUserId)`，直接在 client 上访问业务分组。选项构造后不可再通过 `forUser` 切换身份。HTTP/SSE 的挂起回调由 OkHttp 工作线程桥接，WebSocket 在握手前获取令牌。取消或超时会立即停止等待，迟到的令牌会被丢弃；回调应遵循协程取消语义以释放自身资源。
Hmac options bind credentials and identity directly. Options clients cannot switch users. HTTP/SSE callbacks are bridged through OkHttp workers; WebSocket obtains its token before handshaking. Cancellation and timeout stop waiting immediately and discard late tokens; callbacks should cooperate with cancellation to release their own resources.

令牌有效期十分钟但只认证一次。每个请求、手动重试和 SSE/WebSocket 重连独立调用回调，不缓存、不合并并发签发、不自动重试或跟随重定向；业务失败也不能复用。Token 模式不发送 HMAC 头，不能调用 createOpenApiToken。示例签发函数是你自己的业务接口，应由持有 AK/SK 的可信服务端根据登录态确定用户和渠道。
底层库尝试自动重发时会以 I/O 错误中止；由应用决定是否使用新令牌重试。
Tokens authenticate only once within ten minutes. Each request, explicit retry, and SSE/WebSocket reconnection obtains a fresh token independently. Token clients do not cache, retry automatically, follow redirects, send HMAC headers, or call presign. Your business server issues tokens for the authenticated user and permitted channel.

## Kotlin 快速开始
Kotlin quick start

```kotlin
import cloud.lingya.agents.sdk.AgentsClient
import cloud.lingya.agents.sdk.OpenApiCredentials
import cloud.lingya.agents.sdk.generated.model.AiChatInput

val user = AgentsClient(
    baseUrl = "https://lingtong.lingya.tech/",
    channelId = System.getenv("OPENAPI_CHANNEL_ID"),
    credentials = OpenApiCredentials(
        accessKey = System.getenv("OPENAPI_AK"),
        secretKey = System.getenv("OPENAPI_SK"),
    ),
).forUser("external-user-id")

val submission = user.chat.createChat(AiChatInput(query = "你好"))
```

`channelId` 只在创建 `AgentsClient` 时提供，业务方法不再接收它。
Provide `channelId` only when creating `AgentsClient`; business methods do not accept it.

## SSE 事件流
SSE event stream

```kotlin
import cloud.lingya.agents.sdk.event.AiChatBriefEvent
import cloud.lingya.agents.sdk.generated.model.AiChatStreamInput

user.chat.streamChatEvents(
    conversationId = submission.conversationId,
    input = AiChatStreamInput(messageId = submission.messageId),
).collect { event ->
    when (event) {
        is AiChatBriefEvent.Message -> println(event.value.message)
        is AiChatBriefEvent.Unknown -> println(event.rawJson)
        else -> println(event.type)
    }
}
```

## Java 阻塞调用
Java blocking calls

```java
var client = new AgentsClient(
    "https://lingtong.lingya.tech/",
    System.getenv("OPENAPI_CHANNEL_ID"),
    new OpenApiCredentials(System.getenv("OPENAPI_AK"), System.getenv("OPENAPI_SK"))
);
var user = client.blockingForUser("external-user-id");
```

## API 分组
API groups

可用分组为 `chat`、`configuration`、`conversations`、`events`、`files`、`interactions`、`knowledge`、`messages`、`sql` 和 `workspace`。
Available groups are `chat`, `configuration`, `conversations`, `events`, `files`, `interactions`, `knowledge`, `messages`, `sql`, and `workspace`.

## 错误处理
Error handling

```kotlin
import cloud.lingya.agents.sdk.ApiException

try {
    user.conversations.getConversationTitle("conversation-id")
} catch (error: ApiException) {
    println("${error.kind}: HTTP ${error.statusCode}, ${error.code}, ${error.requestId}")
}
```

请只在可信服务端保存 `secretKey`，不要将其放入浏览器、移动端、桌面端或日志。
Keep `secretKey` on trusted servers only; never put it in browsers, mobile apps, desktop apps, or logs.


## 0.7 请求生命周期与订阅

```kotlin
val options = RequestOptions(timeoutMs = 60_000, idleTimeoutMs = 30_000)
val subscription = client.chat.subscribeChatEvents(conversationId, input, "websocket", options)
try { subscription.events.collect { consume(it) } }
finally { subscription.close(); subscription.closed.await() }
val download = client.sql.exportSqlQueryResultWithMetadata(conversationId, resultId, format, requestOptions = options)
```
协程取消保持 `CancellationException`。阻塞门面保留旧签名，并提供请求选项重载和下载元数据方法。

超时覆盖获取令牌、建连和完整响应；SDK 不自动重试或重连。业务 End 立即释放连接，WebSocket complete/1000 不补造 End。错误包含可判别分类、HTTP 状态和可取得的业务错误及请求标识。详见 [生命周期、错误和生成机制](docs/request-lifecycle.md)。运行时由契约项目统一生成，修改生成产物会被 CI 检出。

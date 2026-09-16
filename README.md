# 灵涯 Agents Kotlin/JVM SDK
Lingya Agents SDK for Kotlin/JVM

用于在可信服务端调用灵涯 Agents OpenAPI。
Use this SDK to call Lingya Agents OpenAPI from a trusted server.

## 安装
Installation

```kotlin
dependencies {
    implementation("cloud.lingya:lingya-agents-sdk:0.4.0")
}
```

运行环境需要 JDK 17 或更高版本。
The runtime requires JDK 17 or later.

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
    println("HTTP ${error.statusCode}: ${error.responseBody}")
}
```

请只在可信服务端保存 `secretKey`，不要将其放入浏览器、移动端、桌面端或日志。
Keep `secretKey` on trusted servers only; never put it in browsers, mobile apps, desktop apps, or logs.

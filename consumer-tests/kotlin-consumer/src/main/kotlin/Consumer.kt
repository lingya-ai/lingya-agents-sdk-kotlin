import cloud.lingya.agents.sdk.LingyaAgentsClient
import cloud.lingya.agents.sdk.OpenApiCredentials
import cloud.lingya.agents.sdk.generated.model.AiChatInput
import cloud.lingya.agents.sdk.generated.model.AiChatStreamInput

fun compileKotlinConsumer(client: LingyaAgentsClient) {
    val user = client.forUser("external-user")
    val input = AiChatInput("hello")
    user.chat.streamChatEvents("conversation", AiChatStreamInput("message"))
    println(input.query)
}

fun createKotlinClient(): LingyaAgentsClient = LingyaAgentsClient(
    "https://tenant.example.com",
    "channel",
    OpenApiCredentials("abcdefghijklmnopqrstuvwxyzABCDEF", "secret"),
)

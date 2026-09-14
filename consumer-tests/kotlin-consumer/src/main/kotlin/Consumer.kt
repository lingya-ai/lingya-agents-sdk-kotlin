import ai.lingya.agents.sdk.LingyaAgentsClient
import ai.lingya.agents.sdk.OpenApiCredentials
import ai.lingya.agents.sdk.generated.model.AiChatInput

fun compileKotlinConsumer(client: LingyaAgentsClient) {
    val user = client.forUser("external-user")
    val input = AiChatInput("hello")
    user.streamChatEvents("conversation", "message")
    println(input.query)
}

fun createKotlinClient(): LingyaAgentsClient = LingyaAgentsClient(
    "https://tenant.example.com",
    "channel",
    OpenApiCredentials("abcdefghijklmnopqrstuvwxyzABCDEF", "secret"),
)

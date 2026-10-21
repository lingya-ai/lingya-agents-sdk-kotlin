import cloud.lingya.agents.sdk.AgentsClient;
import cloud.lingya.agents.sdk.OpenApiCredentials;
import cloud.lingya.agents.sdk.AgentsAuth;
import cloud.lingya.agents.sdk.AgentsClientOptions;
import cloud.lingya.agents.sdk.generated.model.AiChatInput;

public final class Consumer {
    private Consumer() {
    }

    public static void compileJavaConsumer() {
        var tokenClient = new AgentsClient(new AgentsClientOptions(
            "https://tenant.example.com", "channel",
            new AgentsAuth.Token((context, continuation) -> "fresh-token")
        )).blocking();
        tokenClient.getChat();
        var client = new AgentsClient(
            "https://tenant.example.com",
            "channel",
            new OpenApiCredentials("abcdefghijklmnopqrstuvwxyzABCDEF", "secret")
        );
        var user = client.blockingForUser("external-user");
        var input = new AiChatInput("hello", null, null, null, null, null);
        user.getChat().createChat(input);
        input.getQuery();
    }
}

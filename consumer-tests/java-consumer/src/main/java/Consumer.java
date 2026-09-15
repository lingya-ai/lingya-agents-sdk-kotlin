import cloud.lingya.agents.sdk.LingyaAgentsClient;
import cloud.lingya.agents.sdk.OpenApiCredentials;
import cloud.lingya.agents.sdk.generated.model.AiChatInput;

public final class Consumer {
    private Consumer() {
    }

    public static void compileJavaConsumer() {
        var client = new LingyaAgentsClient(
            "https://tenant.example.com",
            "channel",
            new OpenApiCredentials("abcdefghijklmnopqrstuvwxyzABCDEF", "secret")
        );
        var user = client.blockingForUser("external-user");
        var input = new AiChatInput("hello", null, null, null);
        user.getApis();
        input.getQuery();
    }
}

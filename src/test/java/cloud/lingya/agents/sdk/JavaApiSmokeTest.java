package cloud.lingya.agents.sdk;

import cloud.lingya.agents.sdk.generated.model.AiChatInput;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

class JavaApiSmokeTest {
    @Test
    void blockingFacadeIsCallableFromJava() {
        AiChatInput input = new AiChatInput("hello", null, null, null);
        assertNotNull(input.getQuery());
        assertNotNull(RetryPolicy.NONE);
        for (var method : cloud.lingya.agents.sdk.api.BlockingChatApi.class.getDeclaredMethods()) {
            for (var parameter : method.getParameters()) {
                assertFalse(parameter.getName().equals("channelId"));
            }
        }
    }
}

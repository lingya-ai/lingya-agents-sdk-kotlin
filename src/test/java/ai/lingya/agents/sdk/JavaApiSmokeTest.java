package ai.lingya.agents.sdk;

import ai.lingya.agents.sdk.generated.model.AiChatInput;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class JavaApiSmokeTest {
    @Test
    void blockingFacadeIsCallableFromJava() {
        AiChatInput input = new AiChatInput("hello", null, null, null);
        assertNotNull(input.getQuery());
        assertNotNull(RetryPolicy.NONE);
    }
}

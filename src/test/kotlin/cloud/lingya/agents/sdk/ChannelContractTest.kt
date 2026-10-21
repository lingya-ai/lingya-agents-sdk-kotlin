package cloud.lingya.agents.sdk

import cloud.lingya.agents.sdk.generated.model.ConversationUserMessage
import cloud.lingya.agents.sdk.generated.model.UpdateConversationGroupInput
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ChannelContractTest {
    @Test
    fun `ungrouping preserves an explicitly null group id`() {
        assertEquals("{\"groupId\":null}", jacksonObjectMapper().writeValueAsString(UpdateConversationGroupInput(null)))
    }

    @Test
    fun `message metadata preserves nested JSON objects`() {
        val mapper = jacksonObjectMapper()
        val message = mapper.readValue(
            """{"text":"hello","type":"USER","executionType":"USER_MESSAGE","metadata":{"source":{"id":1}}}""",
            ConversationUserMessage::class.java,
        )
        assertEquals(mapOf("source" to mapOf("id" to 1)), message.metadata)
    }
}

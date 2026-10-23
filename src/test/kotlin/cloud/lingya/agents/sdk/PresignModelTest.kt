package cloud.lingya.agents.sdk

import cloud.lingya.agents.sdk.generated.model.GeneratePreSignedUrlInput
import cloud.lingya.agents.sdk.generated.model.GeneratePreSignedUrlOutput
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PresignModelTest {
    private val mapper = jacksonObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)

    @Test
    fun `input serializes size bytes`() {
        val input = GeneratePreSignedUrlInput(
            fileName = "agent-sft-overall-strategy.md",
            module = GeneratePreSignedUrlInput.Module.aiMinusChatMinusAttachments,
            contentMd5 = "BGN0aTOgnUtqM8UMJesATw==",
            sizeBytes = 2048,
        )
        assertEquals(2048L, mapper.readTree(mapper.writeValueAsString(input))["sizeBytes"].asLong())
    }

    @Test
    fun `output restores upload protocol`() {
        val output = mapper.readValue(
            """{"support":true,"fileUk":"file-key","url":"https://objects.example.com/upload","uploadMethod":"POST_FORM","formFields":{"key":"uploads/agent-sft-overall-strategy.md"},"maximumBytes":4096}""",
            GeneratePreSignedUrlOutput::class.java,
        )
        assertEquals(GeneratePreSignedUrlOutput.UploadMethod.POST_FORM, output.uploadMethod)
        assertEquals(mapOf("key" to "uploads/agent-sft-overall-strategy.md"), output.formFields)
        assertEquals(4096L, output.maximumBytes)
    }
}

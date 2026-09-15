package cloud.lingya.agents.sdk

import cloud.lingya.agents.sdk.event.AiChatBriefEvent
import cloud.lingya.agents.sdk.event.AiChatEventDecoder
import cloud.lingya.agents.sdk.event.ToolExtension
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertInstanceOf
import org.junit.jupiter.api.Test

class PolymorphicModelTest {
    private val mapper = jacksonObjectMapper()
        .findAndRegisterModules()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    private val decoder = AiChatEventDecoder(mapper)

    @Test
    fun `decodes all fifteen published event variants`() {
        val documents = listOf(
            """{"type":"user-query","query":"hello","attachments":null}""",
            """{"type":"compressor-context-start"}""",
            """{"type":"compressor-context-end"}""",
            """{"type":"compactor-warning","level":"WARN","warning":"near limit"}""",
            """{"type":"manual-interrupt"}""",
            """{"type":"error","message":"failed"}""",
            """{"type":"start"}""",
            """{"type":"think","message":"reasoning"}""",
            """{"type":"chat-client-request","messages":[],"chatOptions":null}""",
            """{"type":"chat-client-response","assistantMessages":[],"usage":{"inputTokens":1,"outputTokens":2,"totalTokens":3}}""",
            """{"type":"message","message":"answer"}""",
            """{"type":"tool-execution","toolId":"tool-1","toolName":"read","status":"Success","action":"READ","summary":null,"extension":null}""",
            """{"type":"tool-execution-sub-agent-call","toolCallId":"call-1","toolName":"delegate","subAgentConversationId":"conversation-2","subAgentMessageId":"message-2"}""",
            """{"type":"tool-execution-awaiting-user-input","toolCallId":"call-1","toolName":"ask","questionId":"question-1","question":"Continue?","options":[{"text":"Yes","recommended":true}],"multiple":false,"serverNow":"2026-01-01T00:00:00Z","timeoutSeconds":30,"questionDetails":null}""",
            """{"type":"end","executionTimeMillis":10,"totalUsage":{"inputTokens":1,"outputTokens":2,"totalTokens":3},"messageContextUsageRatio":null,"contextWindowUsage":null,"artifacts":[],"nonFileArtifacts":[]}""",
        )

        val events = documents.map(decoder::decode)

        assertEquals(15, events.map(AiChatBriefEvent::type).distinct().size)
        assertInstanceOf(AiChatBriefEvent.UserQuery::class.java, events.first())
        assertInstanceOf(AiChatBriefEvent.End::class.java, events.last())
    }

    @Test
    fun `decodes every published tool extension category`() {
        val contents = listOf(
            "planApproval" to """{"previewFileId":1,"serverNow":"2026-01-01T00:00:00Z","approvalTimeoutAt":"2026-01-01T00:30:00Z"}""",
            "askUserQuestion" to """{"selectedOptions":["Yes"],"customInput":null}""",
            "imageGeneration" to """{"fileId":1}""",
            "sqlQuery" to """{"sql":"select 1"}""",
            "sqlQueryResult" to """{"resultId":"r1","rowCount":1,"totalRowCount":1,"columnCount":1,"columns":["value"],"schema":[{"name":"value","type":"INTEGER","nullable":false,"precision":null,"scale":null}],"truncated":false,"limit":100}""",
            "sqlChartResult" to """{"resultId":"r1","rowCount":1,"columnCount":1,"columns":["value"],"schema":[{"name":"value","type":"INTEGER","nullable":false,"precision":null,"scale":null}],"chart":{"version":1,"type":"BAR","title":"Chart","subtitle":null,"categoryColumn":"value","categoryType":"CATEGORY","orientation":"VERTICAL","series":[],"legend":false,"dataZoom":"NONE","nullPolicy":"GAP"},"qualitySummary":{"nullValueCount":0,"nullValueColumns":[],"zeroFilledValueCount":0},"timeContext":{"asOfInstant":"2026-01-01T00:00:00Z","tenantZoneId":"Asia/Shanghai"}}""",
            "mathFormula" to """{"expression":"1+1","variables":null}""",
            "mathResult" to """{"result":"2"}""",
            "jsRunScript" to """{"script":"1+1"}""",
            "jsRunScriptResult" to """{"result":"2","stdout":""}""",
            "skillResource" to """{"operationType":"CREATE","filepath":"SKILL.md"}""",
            "taskProgress" to """{"action":"CREATE","currentTaskId":"1","currentTaskStatus":"pending","archived":false,"total":1,"pending":1,"inProgress":0,"completed":0,"progressPercent":0,"tasks":[{"id":"1","subject":"test","activeForm":null,"status":"pending","blocks":[],"blockedBy":[]}]}""",
        )

        val extensions = contents.map { (category, content) ->
            val event = decoder.decode(
                """{"type":"tool-execution","toolId":"tool-1","toolName":"test","status":"Success","action":"DEFAULT","summary":null,"extension":{"category":"$category","content":$content,"specialRender":true}}""",
            ) as AiChatBriefEvent.ToolExecution
            requireNotNull(event.value.extension)
        }

        assertEquals(contents.map { it.first }, extensions.map(ToolExtension::category))
    }

    @Test
    fun `preserves unknown event and extension as raw JSON strings`() {
        val futureEvent = decoder.decode("""{"type":"future-event","newField":42}""")
        val toolEvent = decoder.decode(
            """{"type":"tool-execution","toolId":"tool-1","toolName":"future","status":"Success","action":"DEFAULT","extension":{"category":"future-extension","content":{"newField":42},"specialRender":true}}""",
        ) as AiChatBriefEvent.ToolExecution

        val unknownEvent = assertInstanceOf(AiChatBriefEvent.Unknown::class.java, futureEvent)
        val unknownExtension = assertInstanceOf(ToolExtension.Unknown::class.java, toolEvent.value.extension)
        assertEquals(true, unknownEvent.rawJson.contains("\"newField\":42"))
        assertEquals(true, unknownExtension.rawJson.contains("\"newField\":42"))
    }
}

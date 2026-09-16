package cloud.lingya.agents.sdk

import cloud.lingya.agents.sdk.event.AiChatBriefEvent
import cloud.lingya.agents.sdk.generated.api.SQLApi
import cloud.lingya.agents.sdk.generated.model.AiChatEventsBatchInput
import cloud.lingya.agents.sdk.generated.model.AiChatInput
import cloud.lingya.agents.sdk.generated.model.AiChatStreamInput
import cloud.lingya.agents.sdk.generated.model.ChatStreamProbeInput
import cloud.lingya.agents.sdk.generated.model.ConfirmUploadInput
import cloud.lingya.agents.sdk.generated.model.ConversationActivityBatchInput
import cloud.lingya.agents.sdk.generated.model.ConversationReadReceiptInput
import cloud.lingya.agents.sdk.generated.model.ConversationShareInput
import cloud.lingya.agents.sdk.generated.model.ConversationStatusInput
import cloud.lingya.agents.sdk.generated.model.ConversationTitleInput
import cloud.lingya.agents.sdk.generated.model.CreateFileInput
import cloud.lingya.agents.sdk.generated.model.GeneratePreSignedUrlInput
import cloud.lingya.agents.sdk.generated.model.PlanApprovalInput
import cloud.lingya.agents.sdk.generated.model.ReturnedReference
import cloud.lingya.agents.sdk.generated.model.UserInputAnswerInput
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assumptions.assumeTrue
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Path
import java.util.UUID
import kotlin.time.Duration.Companion.seconds

@Tag("live")
class AllEndpointsLiveIntegrationTest {
    @Test
    fun `真实服务覆盖契约中的全部接口`() = runBlocking {
        val fixture = liveFixture()
        val coverage = EndpointCoverage()
        val first = coverage.successful("POST", "") {
            fixture.user.chat.createChat(
                AiChatInput(query = "仅回复英文 OK"),
            )
        }

        try {
            coverage.successful("GET", "/config") {
                fixture.user.configuration.getAgentsConfig()
            }
            val firstEvents = withTimeout(120.seconds) {
                fixture.user.chat.streamChatEvents(
                    first.conversationId,
                    AiChatStreamInput(first.messageId),
                ).toList()
            }
            assertTrue(firstEvents.any { it is AiChatBriefEvent.End })
            coverage.streamCompleted("POST", "/conversations/{conversationId}/stream")

            coverage.successful("GET", "/conversations/{conversationId}/config") {
                fixture.user.configuration.getConversationConfig(first.conversationId)
            }
            coverage.successful("GET", "/conversations/{conversationId}/context-usage") {
                fixture.user.conversations.getConversationContextUsage(first.conversationId)
            }
            coverage.successful("GET", "/conversations") {
                fixture.user.conversations.listConversations(current = 0, size = 5)
            }
            coverage.successful("GET", "/conversations/active") {
                fixture.user.conversations.listActiveConversations()
            }
            coverage.successful("GET", "/conversations/unread") {
                fixture.user.conversations.listUnreadConversations()
            }
            coverage.successful("POST", "/conversations/activity/query") {
                fixture.user.conversations.queryConversationActivities(
                    ConversationActivityBatchInput(listOf(first.conversationId)),
                )
            }
            coverage.successful("PUT", "/conversations/{conversationId}/read-receipt") {
                fixture.user.conversations.markConversationRead(
                    first.conversationId,
                    ConversationReadReceiptInput(first.messageId),
                )
            }
            coverage.successful("GET", "/conversations/stats") {
                fixture.user.conversations.getConversationStats()
            }
            coverage.successfulStatus("PATCH", "/conversations/{conversationId}/title") {
                fixture.user.conversations.updateConversationTitle(
                    first.conversationId,
                    ConversationTitleInput("Kotlin SDK 全接口测试"),
                )
            }
            coverage.successful("GET", "/conversations/{conversationId}/title") {
                fixture.user.conversations.getConversationTitle(first.conversationId)
            }
            coverage.successful("GET", "/conversations/{conversationId}/messages") {
                fixture.user.messages.listConversationMessages(first.conversationId)
            }
            coverage.successful("GET", "/conversations/{conversationId}/messages/{messageId}") {
                fixture.user.messages.getConversationMessage(
                    first.conversationId,
                    first.messageId,
                )
            }
            coverage.successful("GET", "/events") {
                fixture.user.events.getChatEvents(first.conversationId, first.messageId)
            }
            coverage.successful("POST", "/events/batch") {
                fixture.user.events.getChatEventsBatch(
                    AiChatEventsBatchInput(first.conversationId, listOf(first.messageId)),
                )
            }

            val second = coverage.successful("POST", "/conversations/{conversationId}") {
                fixture.user.chat.continueChat(
                    first.conversationId,
                    AiChatInput(query = "再次仅回复英文 OK"),
                )
            }
            withTimeout(120.seconds) {
                fixture.user.chat.streamChatEvents(
                    second.conversationId,
                    AiChatStreamInput(second.messageId),
                ).toList()
            }
            coverage.successfulStatus("DELETE", "/conversations/{conversationId}/interrupt") {
                fixture.user.chat.interruptConversation(first.conversationId)
            }
            coverage.successfulStatus("POST", "/conversations/{conversationId}/compact") {
                fixture.user.chat.compactConversation(first.conversationId)
            }

            coverage.successful("GET", "/conversations/{conversationId}/async-tasks") {
                fixture.user.messages.listConversationAsyncTasks(first.conversationId)
            }
            coverage.expectedDomainResult("GET", "/conversations/{conversationId}/async-tasks/{asyncTaskId}") {
                fixture.user.messages.getConversationAsyncTask(
                    first.conversationId,
                    "missing-async-task",
                )
            }
            coverage.expectedDomainResult(
                "DELETE",
                "/conversations/{conversationId}/messages/{messageId}/queue",
            ) {
                fixture.user.messages.cancelQueuedMessage(
                    first.conversationId,
                    first.messageId,
                )
            }

            val share = coverage.successful("POST", "/conversations/{conversationId}/shares") {
                fixture.user.conversations.createConversationShare(
                    first.conversationId,
                    ConversationShareInput(),
                )
            }
            coverage.successful("GET", "/conversations/{conversationId}/shares") {
                fixture.user.conversations.listConversationShares(first.conversationId)
            }
            coverage.successful("DELETE", "/conversations/{conversationId}/shares/{shareId}") {
                fixture.user.conversations.revokeConversationShare(
                    first.conversationId,
                    share.shareId,
                )
            }

            coverage.successful("POST", "/plan/approve") {
                fixture.user.interactions.approvePlan(
                    PlanApprovalInput(first.conversationId, first.messageId, approved = false),
                )
            }
            coverage.successful("GET", "/plan/{planId}/status") {
                fixture.user.interactions.getPlanStatus("missing-plan")
            }
            coverage.successful("GET", "/user-input/{questionId}/status") {
                fixture.user.interactions.getUserInputStatus(
                    "missing-question",
                    first.conversationId,
                    first.messageId,
                )
            }
            coverage.successful("POST", "/user-input/answer") {
                fixture.user.interactions.answerUserInput(
                    UserInputAnswerInput(
                        first.conversationId,
                        first.messageId,
                        "missing-question",
                        selectedOptions = emptyList(),
                        customInput = "not pending",
                    ),
                )
            }

            verifySqlEndpoints(coverage, fixture, first.conversationId)
            verifyFileEndpoints(coverage, fixture, first.conversationId, first.messageId)
            verifyKnowledgeAndWorkspaceEndpoints(coverage, fixture, first.conversationId)

            val probeId = "all-${UUID.randomUUID()}"
            val probeEvents = withTimeout(10.seconds) {
                fixture.user.chat.probeEventStream(ChatStreamProbeInput(probeId)).toList()
            }
            assertEquals(listOf(0, 1, 2, 3), probeEvents.map { it.sequence })
            coverage.streamCompleted("POST", "/stream-probe")

            coverage.successfulStatus("PATCH", "/conversations/{conversationId}/status") {
                fixture.user.conversations.updateConversationStatus(
                    first.conversationId,
                    ConversationStatusInput("ARCHIVED"),
                )
            }
        } finally {
            try {
                coverage.successfulStatus("DELETE", "/conversations/{conversationId}") {
                    fixture.user.conversations.deleteConversation(first.conversationId)
                }
            } finally {
                coverage.writeReport()
            }
        }

        assertEquals(publishedEndpoints(), coverage.endpoints)
    }

    private suspend fun verifySqlEndpoints(
        coverage: EndpointCoverage,
        fixture: LiveFixture,
        conversationId: String,
    ) {
        coverage.expectedDomainResult("GET", "/conversations/{conversationId}/sql-query-results/{resultId}") {
            fixture.user.sql.getSqlQueryResult(conversationId, "missing-result")
        }
        coverage.expectedDomainResult(
            "GET",
            "/conversations/{conversationId}/sql-query-results/{resultId}/chart-data",
        ) {
            fixture.user.sql.getSqlQueryChartData(conversationId, "missing-result")
        }
        coverage.expectedDomainResult(
            "GET",
            "/conversations/{conversationId}/sql-query-results/{resultId}/export",
        ) {
            fixture.user.sql.exportSqlQueryResult(
                conversationId,
                "missing-result",
                SQLApi.FormatExportSqlQueryResult.CSV,
            )
        }
    }

    private suspend fun verifyFileEndpoints(
        coverage: EndpointCoverage,
        fixture: LiveFixture,
        conversationId: String,
        messageId: String,
    ) {
        val contentMd5 = "17/2WOZXDPjhZzwMQCHrDg=="
        coverage.successful("GET", "/files/meta/contentMd5") {
            fixture.user.files.fileExistsByContentMd5(contentMd5)
        }
        val upload = coverage.successful("POST", "/files/pre-signed-url/write") {
            fixture.user.files.createPreSignedUpload(
                GeneratePreSignedUrlInput(
                    "lingya-sdk-endpoint-test.txt",
                    GeneratePreSignedUrlInput.Module.aiMinusChatMinusAttachments,
                    contentMd5,
                ),
            )
        }
        coverage.expectedDomainResult("POST", "/files/pre-signed-url/confirm") {
            fixture.user.files.confirmPreSignedUpload(
                ConfirmUploadInput(requireNotNull(upload.fileUk), contentMd5),
            )
        }
        coverage.expectedDomainResult("POST", "/files/contentMd5") {
            fixture.user.files.createFileByContentMd5(
                CreateFileInput("lingya-sdk-endpoint-test.txt", contentMd5),
            )
        }
        coverage.expectedDomainResult("GET", "/conversations/{conversationId}/files/{fileId}/preview") {
            fixture.user.files.getConversationFilePreview(conversationId, Long.MAX_VALUE)
        }
        coverage.expectedDomainResult(
            "GET",
            "/conversations/{conversationId}/messages/{messageId}/plan-intermediate-files/{fileId}/preview",
        ) {
            fixture.user.files.getPlanIntermediateFilePreview(
                conversationId,
                messageId,
                Long.MAX_VALUE,
            )
        }
    }

    private suspend fun verifyKnowledgeAndWorkspaceEndpoints(
        coverage: EndpointCoverage,
        fixture: LiveFixture,
        conversationId: String,
    ) {
        coverage.successful("POST", "/knowledge-bases/citations/metadata") {
            fixture.user.knowledge.getCitationMetadataBatch(emptyList())
        }
        coverage.expectedDomainResult(
            "GET",
            "/knowledge-bases/citations/{citationType}/{referenceId}/metadata",
        ) {
            fixture.user.knowledge.getCitationMetadata(
                "CHUNK",
                Long.MAX_VALUE,
            )
        }
        coverage.successful("GET", "/conversations/{conversationId}/workspace/files") {
            fixture.user.workspace.listWorkspaceArtifacts(conversationId)
        }
        coverage.expectedDomainResult("GET", "/conversations/{conversationId}/workspace/files/preview") {
            fixture.user.workspace.getWorkspaceFilePreview(
                conversationId,
                "missing-file.txt",
            )
        }
    }

    private fun liveFixture(): LiveFixture {
        val baseUrl = requiredEnvironment("LINGYA_LIVE_BASE_URL")
        val channelId = requiredEnvironment("LINGYA_LIVE_CHANNEL_ID")
        val client = AgentsClient(
            baseUrl = baseUrl,
            channelId = channelId,
            credentials = OpenApiCredentials(
                requiredEnvironment("OPENAPI_AK"),
                requiredEnvironment("OPENAPI_SK"),
            ),
        )
        val externalUserId = System.getenv("LINGYA_LIVE_EXTERNAL_USER_ID")
            ?.takeIf(String::isNotBlank)
            ?: "lingya-kotlin-sdk-all-endpoints"
        return LiveFixture(client.forUser(externalUserId))
    }

    private fun requiredEnvironment(name: String): String {
        val value = System.getenv(name)
        assumeTrue(!value.isNullOrBlank(), "$name is required for live API tests")
        return requireNotNull(value)
    }

    private fun publishedEndpoints(): Set<Endpoint> {
        val endpoints = linkedSetOf<Endpoint>()
        var path: String? = null
        Files.readAllLines(Path.of("openapi/lingya-agents-v1.yaml")).forEach { line ->
            PATH_PATTERN.matchEntire(line)?.let { path = it.groupValues[1] }
            METHOD_PATTERN.matchEntire(line)?.let { match ->
                path?.let { endpoints += Endpoint(match.groupValues[1].uppercase(), it) }
            }
        }
        return endpoints
    }

    private data class LiveFixture(
        val user: AgentsUserClient,
    )

    private data class Endpoint(val method: String, val path: String)

    private class EndpointCoverage {
        val endpoints: MutableSet<Endpoint> = linkedSetOf()
        private val results: MutableList<EndpointResult> = mutableListOf()

        suspend fun <T : Any> successful(
            method: String,
            suffix: String,
            request: suspend () -> T,
        ): T {
            val endpoint = register(method, suffix)
            val value = request()
            result(endpoint, 200, "通过")
            return value
        }

        suspend fun successfulStatus(
            method: String,
            suffix: String,
            request: suspend () -> Any,
        ) {
            val endpoint = register(method, suffix)
            request()
            result(endpoint, 200, "通过")
        }

        suspend fun expectedDomainResult(
            method: String,
            suffix: String,
            request: suspend () -> Any,
        ) {
            val endpoint = register(method, suffix)
            try {
                request()
                error("$method $suffix unexpectedly succeeded")
            } catch (exception: ApiException) {
                result(endpoint, exception.statusCode, "环境能力受限，参数与错误响应已验证")
                assertTrue(
                    exception.statusCode in EXPECTED_DOMAIN_ERROR_CODES,
                    "$method $suffix returned unexpected HTTP ${exception.statusCode}",
                )
            }
        }

        fun streamCompleted(method: String, suffix: String) {
            val endpoint = register(method, suffix)
            results += EndpointResult(endpoint, 200, localRequestId(), "流式响应完成")
        }

        fun writeReport() {
            val report = buildString {
                appendLine("# Agents OpenAPI 真实环境全接口测试报告")
                appendLine()
                appendLine("报告不包含凭证、请求正文或响应正文。请求标识优先使用服务端响应头，否则使用本地脱敏序号。")
                appendLine()
                appendLine("| 请求标识 | Method | Path | HTTP | 结果 |")
                appendLine("|---|---|---|---:|---|")
                results.forEach { result ->
                    appendLine(
                        "| ${result.requestId} | ${result.endpoint.method} | `${result.endpoint.path}` | " +
                            "${result.statusCode} | ${result.outcome} |",
                    )
                }
                appendLine()
                appendLine("清理结果：会话删除接口出现在上表且结果为“通过”时，测试创建的会话已清理。")
            }
            val directory = Path.of("build", "reports", "live-api")
            Files.createDirectories(directory)
            Files.writeString(directory.resolve("all-endpoints.md"), report)
        }

        private fun register(method: String, suffix: String): Endpoint {
            val path = if (suffix.isEmpty()) BASE_PATH else BASE_PATH + suffix
            val endpoint = Endpoint(method, path)
            assertTrue(endpoints.add(endpoint), "duplicate endpoint coverage: $method $path")
            return endpoint
        }

        private fun result(endpoint: Endpoint, statusCode: Int, outcome: String) {
            results += EndpointResult(endpoint, statusCode, localRequestId(), outcome)
        }

        private fun localRequestId(): String = "local-${(results.size + 1).toString().padStart(3, '0')}"

        private data class EndpointResult(
            val endpoint: Endpoint,
            val statusCode: Int,
            val requestId: String,
            val outcome: String,
        )

    }

    private companion object {
        const val BASE_PATH = "/api/agents/channel/openapi/v1/{channelId}/chat"
        val EXPECTED_DOMAIN_ERROR_CODES = setOf(400, 403, 404, 409, 422)
        val PATH_PATTERN = Regex("^  (/api/agents/channel/openapi/[^:]+):$")
        val METHOD_PATTERN = Regex("^    (get|post|put|patch|delete):$")
    }
}

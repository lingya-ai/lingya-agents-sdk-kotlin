package ai.lingya.agents.sdk

import ai.lingya.agents.sdk.event.AiChatBriefEvent
import ai.lingya.agents.sdk.generated.api.SQLApi
import ai.lingya.agents.sdk.generated.model.AiChatEventsBatchInput
import ai.lingya.agents.sdk.generated.model.AiChatInput
import ai.lingya.agents.sdk.generated.model.ConfirmUploadInput
import ai.lingya.agents.sdk.generated.model.ConversationActivityBatchInput
import ai.lingya.agents.sdk.generated.model.ConversationReadReceiptInput
import ai.lingya.agents.sdk.generated.model.ConversationShareInput
import ai.lingya.agents.sdk.generated.model.ConversationStatusInput
import ai.lingya.agents.sdk.generated.model.ConversationTitleInput
import ai.lingya.agents.sdk.generated.model.CreateFileInput
import ai.lingya.agents.sdk.generated.model.GeneratePreSignedUrlInput
import ai.lingya.agents.sdk.generated.model.PlanApprovalInput
import ai.lingya.agents.sdk.generated.model.ReturnedReference
import ai.lingya.agents.sdk.generated.model.UserInputAnswerInput
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assumptions.assumeTrue
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import retrofit2.Response
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
            fixture.user.apis.chat.createChat(
                fixture.channelId,
                AiChatInput(query = "仅回复英文 OK"),
            )
        }

        try {
            coverage.successful("GET", "/config") {
                fixture.user.apis.configuration.getAgentsConfig(fixture.channelId)
            }
            coverage.record("POST", "/conversations/{conversationId}/stream")
            val firstEvents = withTimeout(120.seconds) {
                fixture.user.streamChatEvents(first.conversationId, first.messageId).toList()
            }
            assertTrue(firstEvents.any { it is AiChatBriefEvent.End })

            coverage.successful("GET", "/conversations/{conversationId}/config") {
                fixture.user.apis.configuration.getConversationConfig(fixture.channelId, first.conversationId)
            }
            coverage.successful("GET", "/conversations/{conversationId}/context-usage") {
                fixture.user.apis.conversations.getConversationContextUsage(fixture.channelId, first.conversationId)
            }
            coverage.successful("GET", "/conversations") {
                fixture.user.apis.conversations.listConversations(fixture.channelId, current = 0, size = 5)
            }
            coverage.successful("GET", "/conversations/active") {
                fixture.user.apis.conversations.listActiveConversations(fixture.channelId)
            }
            coverage.successful("GET", "/conversations/unread") {
                fixture.user.apis.conversations.listUnreadConversations(fixture.channelId)
            }
            coverage.successful("POST", "/conversations/activity/query") {
                fixture.user.apis.conversations.queryConversationActivities(
                    fixture.channelId,
                    ConversationActivityBatchInput(listOf(first.conversationId)),
                )
            }
            coverage.successful("PUT", "/conversations/{conversationId}/read-receipt") {
                fixture.user.apis.conversations.markConversationRead(
                    fixture.channelId,
                    first.conversationId,
                    ConversationReadReceiptInput(first.messageId),
                )
            }
            coverage.successful("GET", "/conversations/stats") {
                fixture.user.apis.conversations.getConversationStats(fixture.channelId)
            }
            coverage.successfulStatus("PATCH", "/conversations/{conversationId}/title") {
                fixture.user.apis.conversations.updateConversationTitle(
                    fixture.channelId,
                    first.conversationId,
                    ConversationTitleInput("Kotlin SDK 全接口测试"),
                )
            }
            coverage.successful("GET", "/conversations/{conversationId}/title") {
                fixture.user.apis.conversations.getConversationTitle(fixture.channelId, first.conversationId)
            }
            coverage.successful("GET", "/conversations/{conversationId}/messages") {
                fixture.user.apis.messages.listConversationMessages(fixture.channelId, first.conversationId)
            }
            coverage.successful("GET", "/conversations/{conversationId}/messages/{messageId}") {
                fixture.user.apis.messages.getConversationMessage(
                    fixture.channelId,
                    first.conversationId,
                    first.messageId,
                )
            }
            coverage.successful("GET", "/events") {
                fixture.user.apis.events.getChatEvents(fixture.channelId, first.conversationId, first.messageId)
            }
            coverage.successful("POST", "/events/batch") {
                fixture.user.apis.events.getChatEventsBatch(
                    fixture.channelId,
                    AiChatEventsBatchInput(first.conversationId, listOf(first.messageId)),
                )
            }

            val second = coverage.successful("POST", "/conversations/{conversationId}") {
                fixture.user.apis.chat.continueChat(
                    fixture.channelId,
                    first.conversationId,
                    AiChatInput(query = "再次仅回复英文 OK"),
                )
            }
            withTimeout(120.seconds) {
                fixture.user.streamChatEvents(second.conversationId, second.messageId).toList()
            }
            coverage.successfulStatus("DELETE", "/conversations/{conversationId}/interrupt") {
                fixture.user.apis.chat.interruptConversation(fixture.channelId, first.conversationId)
            }
            coverage.successfulStatus("POST", "/conversations/{conversationId}/compact") {
                fixture.user.apis.chat.compactConversation(fixture.channelId, first.conversationId)
            }

            coverage.successful("GET", "/conversations/{conversationId}/async-tasks") {
                fixture.user.apis.messages.listConversationAsyncTasks(fixture.channelId, first.conversationId)
            }
            coverage.expectedDomainResult("GET", "/conversations/{conversationId}/async-tasks/{asyncTaskId}") {
                fixture.user.apis.messages.getConversationAsyncTask(
                    fixture.channelId,
                    first.conversationId,
                    "missing-async-task",
                )
            }
            coverage.expectedDomainResult(
                "DELETE",
                "/conversations/{conversationId}/messages/{messageId}/queue",
            ) {
                fixture.user.apis.messages.cancelQueuedMessage(
                    fixture.channelId,
                    first.conversationId,
                    first.messageId,
                )
            }

            val share = coverage.successful("POST", "/conversations/{conversationId}/shares") {
                fixture.user.apis.conversations.createConversationShare(
                    fixture.channelId,
                    first.conversationId,
                    ConversationShareInput(),
                )
            }
            coverage.successful("GET", "/conversations/{conversationId}/shares") {
                fixture.user.apis.conversations.listConversationShares(fixture.channelId, first.conversationId)
            }
            coverage.successful("DELETE", "/conversations/{conversationId}/shares/{shareId}") {
                fixture.user.apis.conversations.revokeConversationShare(
                    fixture.channelId,
                    first.conversationId,
                    share.shareId,
                )
            }

            coverage.successful("POST", "/plan/approve") {
                fixture.user.apis.interactions.approvePlan(
                    fixture.channelId,
                    PlanApprovalInput(first.conversationId, first.messageId, approved = false),
                )
            }
            coverage.successful("GET", "/plan/{planId}/status") {
                fixture.user.apis.interactions.getPlanStatus(fixture.channelId, "missing-plan")
            }
            coverage.successful("GET", "/user-input/{questionId}/status") {
                fixture.user.apis.interactions.getUserInputStatus(
                    fixture.channelId,
                    "missing-question",
                    first.conversationId,
                    first.messageId,
                )
            }
            coverage.successful("POST", "/user-input/answer") {
                fixture.user.apis.interactions.answerUserInput(
                    fixture.channelId,
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

            coverage.record("POST", "/stream-probe")
            val probeId = "all-${UUID.randomUUID()}"
            val probeEvents = withTimeout(10.seconds) {
                fixture.user.probeEventStream(probeId).toList()
            }
            assertEquals(listOf(0, 1, 2, 3), probeEvents.map { it.sequence })

            coverage.successfulStatus("PATCH", "/conversations/{conversationId}/status") {
                fixture.user.apis.conversations.updateConversationStatus(
                    fixture.channelId,
                    first.conversationId,
                    ConversationStatusInput("ARCHIVED"),
                )
            }
        } finally {
            coverage.successfulStatus("DELETE", "/conversations/{conversationId}") {
                fixture.user.apis.conversations.deleteConversation(fixture.channelId, first.conversationId)
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
            fixture.user.apis.sql.getSqlQueryResult(fixture.channelId, conversationId, "missing-result")
        }
        coverage.expectedDomainResult(
            "GET",
            "/conversations/{conversationId}/sql-query-results/{resultId}/chart-data",
        ) {
            fixture.user.apis.sql.getSqlQueryChartData(fixture.channelId, conversationId, "missing-result")
        }
        coverage.expectedDomainResult(
            "GET",
            "/conversations/{conversationId}/sql-query-results/{resultId}/export",
        ) {
            fixture.user.apis.sql.exportSqlQueryResult(
                fixture.channelId,
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
            fixture.user.apis.files.fileExistsByContentMd5(fixture.channelId, contentMd5)
        }
        val upload = coverage.successful("POST", "/files/pre-signed-url/write") {
            fixture.user.apis.files.createPreSignedUpload(
                fixture.channelId,
                GeneratePreSignedUrlInput(
                    "lingya-sdk-endpoint-test.txt",
                    GeneratePreSignedUrlInput.Module.aiMinusChatMinusAttachments,
                    contentMd5,
                ),
            )
        }
        coverage.expectedDomainResult("POST", "/files/pre-signed-url/confirm") {
            fixture.user.apis.files.confirmPreSignedUpload(
                fixture.channelId,
                ConfirmUploadInput(requireNotNull(upload.fileUk), contentMd5),
            )
        }
        coverage.expectedDomainResult("POST", "/files/contentMd5") {
            fixture.user.apis.files.createFileByContentMd5(
                fixture.channelId,
                CreateFileInput("lingya-sdk-endpoint-test.txt", contentMd5),
            )
        }
        coverage.expectedDomainResult("GET", "/conversations/{conversationId}/files/{fileId}/preview") {
            fixture.user.apis.files.getConversationFilePreview(fixture.channelId, conversationId, Long.MAX_VALUE)
        }
        coverage.expectedDomainResult(
            "GET",
            "/conversations/{conversationId}/messages/{messageId}/plan-intermediate-files/{fileId}/preview",
        ) {
            fixture.user.apis.files.getPlanIntermediateFilePreview(
                fixture.channelId,
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
            fixture.user.apis.knowledge.getCitationMetadataBatch(fixture.channelId, emptyList())
        }
        coverage.expectedDomainResult(
            "GET",
            "/knowledge-bases/citations/{citationType}/{referenceId}/metadata",
        ) {
            fixture.user.apis.knowledge.getCitationMetadata(
                fixture.channelId,
                "CHUNK",
                Long.MAX_VALUE,
            )
        }
        coverage.successful("GET", "/conversations/{conversationId}/workspace/files") {
            fixture.user.apis.workspace.listWorkspaceArtifacts(fixture.channelId, conversationId)
        }
        coverage.expectedDomainResult("GET", "/conversations/{conversationId}/workspace/files/preview") {
            fixture.user.apis.workspace.getWorkspaceFilePreview(
                fixture.channelId,
                conversationId,
                "missing-file.txt",
            )
        }
    }

    private fun liveFixture(): LiveFixture {
        val baseUrl = requiredEnvironment("LINGYA_LIVE_BASE_URL")
        val channelId = requiredEnvironment("LINGYA_LIVE_CHANNEL_ID")
        val client = LingyaAgentsClient(
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
        return LiveFixture(channelId, client.forUser(externalUserId))
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
        val channelId: String,
        val user: LingyaAgentsUserClient,
    )

    private data class Endpoint(val method: String, val path: String)

    private class EndpointCoverage {
        val endpoints: MutableSet<Endpoint> = linkedSetOf()

        suspend fun <T : Any> successful(
            method: String,
            suffix: String,
            request: suspend () -> Response<T>,
        ): T {
            record(method, suffix)
            val response = request()
            assertTrue(
                response.isSuccessful,
                "$method $suffix returned HTTP ${response.code()}: ${response.errorBody()?.string()?.take(500)}",
            )
            return requireNotNull(response.body()) { "$method $suffix returned an empty successful response" }
        }

        suspend fun successfulStatus(
            method: String,
            suffix: String,
            request: suspend () -> Response<*>,
        ) {
            record(method, suffix)
            val response = request()
            assertTrue(response.isSuccessful, "$method $suffix returned HTTP ${response.code()}")
        }

        suspend fun expectedDomainResult(
            method: String,
            suffix: String,
            request: suspend () -> Response<*>,
        ) {
            record(method, suffix)
            val response = request()
            assertTrue(
                response.code() in EXPECTED_DOMAIN_ERROR_CODES,
                "$method $suffix returned unexpected HTTP ${response.code()}",
            )
        }

        fun record(method: String, suffix: String) {
            val path = if (suffix.isEmpty()) BASE_PATH else BASE_PATH + suffix
            assertTrue(endpoints.add(Endpoint(method, path)), "duplicate endpoint coverage: $method $path")
        }
    }

    private companion object {
        const val BASE_PATH = "/api/agents/channel/openapi/v1/{channelId}/chat"
        val EXPECTED_DOMAIN_ERROR_CODES = setOf(400, 403, 404, 409, 422)
        val PATH_PATTERN = Regex("^  (/api/agents/channel/openapi/[^:]+):$")
        val METHOD_PATTERN = Regex("^    (get|post|put|patch|delete):$")
    }
}

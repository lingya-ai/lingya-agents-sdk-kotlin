package cloud.lingya.agents.sdk

import cloud.lingya.agents.sdk.api.ChatApi
import cloud.lingya.agents.sdk.api.ConfigurationApi
import cloud.lingya.agents.sdk.api.ConversationsApi
import cloud.lingya.agents.sdk.api.EventsApi
import cloud.lingya.agents.sdk.api.FilesApi
import cloud.lingya.agents.sdk.api.InteractionsApi
import cloud.lingya.agents.sdk.api.KnowledgeApi
import cloud.lingya.agents.sdk.api.MessagesApi
import cloud.lingya.agents.sdk.api.SqlApi
import cloud.lingya.agents.sdk.api.WorkspaceApi
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import java.lang.reflect.Modifier
import kotlin.reflect.KClass

class BoundFacadeCoverageTest {
    @Test
    fun `每个契约接口只生成一个不含 channelId 的公开方法`() {
        val manifest: List<OperationManifest> =
            java.io.File("openapi/endpoints.json").inputStream().use { input ->
                jacksonObjectMapper()
                    .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                    .readValue(input)
            }
        val groups = mapOf(
            "chat" to ChatApi::class,
            "configuration" to ConfigurationApi::class,
            "conversations" to ConversationsApi::class,
            "events" to EventsApi::class,
            "files" to FilesApi::class,
            "interactions" to InteractionsApi::class,
            "knowledge" to KnowledgeApi::class,
            "messages" to MessagesApi::class,
            "sql" to SqlApi::class,
            "workspace" to WorkspaceApi::class,
        )

        assertEquals(46, manifest.size)
        for ((group, type) in groups) {
            val expected = manifest.filter { it.group == group }.map { it.operationId }.toSet()
            val methods = publicBusinessMethods(type)
            assertEquals(expected, methods.map { it.name }.toSet(), group)
            methods.flatMap { it.parameters.asList() }.forEach { parameter ->
                assertFalse(parameter.name == "channelId", "${type.simpleName}.${parameter.name}")
            }
        }
    }

    private fun publicBusinessMethods(type: KClass<*>): List<java.lang.reflect.Method> =
        type.java.declaredMethods.filter { method ->
            Modifier.isPublic(method.modifiers) && !method.isSynthetic && '$' !in method.name
        }

    private data class OperationManifest(
        val operationId: String,
        val group: String,
    )
}

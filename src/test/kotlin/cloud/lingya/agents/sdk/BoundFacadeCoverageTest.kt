package cloud.lingya.agents.sdk

import cloud.lingya.agents.sdk.api.LingyaChatApi
import cloud.lingya.agents.sdk.api.LingyaConfigurationApi
import cloud.lingya.agents.sdk.api.LingyaConversationsApi
import cloud.lingya.agents.sdk.api.LingyaEventsApi
import cloud.lingya.agents.sdk.api.LingyaFilesApi
import cloud.lingya.agents.sdk.api.LingyaInteractionsApi
import cloud.lingya.agents.sdk.api.LingyaKnowledgeApi
import cloud.lingya.agents.sdk.api.LingyaMessagesApi
import cloud.lingya.agents.sdk.api.LingyaSqlApi
import cloud.lingya.agents.sdk.api.LingyaWorkspaceApi
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
            "chat" to LingyaChatApi::class,
            "configuration" to LingyaConfigurationApi::class,
            "conversations" to LingyaConversationsApi::class,
            "events" to LingyaEventsApi::class,
            "files" to LingyaFilesApi::class,
            "interactions" to LingyaInteractionsApi::class,
            "knowledge" to LingyaKnowledgeApi::class,
            "messages" to LingyaMessagesApi::class,
            "sql" to LingyaSqlApi::class,
            "workspace" to LingyaWorkspaceApi::class,
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

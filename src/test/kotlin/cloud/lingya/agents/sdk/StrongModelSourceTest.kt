package cloud.lingya.agents.sdk

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Path

/** Ensures regenerated public models never silently fall back to untyped JSON containers. */
class StrongModelSourceTest {
    @Test
    fun `production models contain no untyped JSON representations`() {
        val forbidden = listOf(
            Regex("\\bJsonNode\\b") to "JsonNode",
            Regex("\\breadTree\\s*\\(") to "readTree",
            Regex("(?:kotlin\\.collections\\.)?Map\\s*<") to "Map model",
            Regex("(?:kotlin\\.collections\\.)?List\\s*<\\s*Any\\??\\s*>") to "List<Any>",
            Regex("\\bHashMap\\b") to "HashMap inheritance",
            Regex("(?:val|var)\\s+\\w+\\s*:\\s*Any\\??") to "Any property",
        )
        val violations = listOf(Path.of("src/main/kotlin"), Path.of("generated/src/main/kotlin"))
            .flatMap { root -> Files.walk(root).use { paths -> paths.filter { it.toString().endsWith(".kt") }.toList() } }
            .flatMap { source ->
                Files.readAllLines(source).mapIndexedNotNull { index, line ->
                    forbidden.firstOrNull { (pattern) -> pattern.containsMatchIn(line) }
                        ?.let { (_, label) -> "$source:${index + 1}: $label" }
                }
            }

        assertTrue(violations.isEmpty(), violations.joinToString("\n"))
    }
}

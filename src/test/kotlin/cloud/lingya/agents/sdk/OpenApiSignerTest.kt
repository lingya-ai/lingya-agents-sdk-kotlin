package cloud.lingya.agents.sdk

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.nio.file.Path
import java.util.Base64

class OpenApiSignerTest {
    @Test
    fun `matches published golden vectors`() {
        val vectors = jacksonObjectMapper().readValue<GoldenVectors>(
            Path.of("openapi", "hmac-v1.json").toFile(),
        )

        vectors.cases.forEach { vector ->
            val input = SigningInput(
                vector.accessKey,
                vector.timestamp.toLong(),
                vector.nonce,
                vector.method,
                vector.rawPath,
                vector.rawQuery,
                vector.encodedUser,
                vector.contentType,
                Base64.getDecoder().decode(vector.bodyBase64),
            )
            assertEquals(vector.canonical, OpenApiSigner.canonicalRequest(input), vector.name)
            assertEquals(vector.signature, OpenApiCredentials(vector.accessKey, vector.secret).sign(input), vector.name)
        }
    }

    private data class GoldenVectors(val version: String, val cases: List<GoldenVector>)

    private data class GoldenVector(
        val name: String,
        val accessKey: String,
        val secret: String,
        val timestamp: String,
        val nonce: String,
        val method: String,
        val rawPath: String,
        val rawQuery: String,
        val encodedUser: String,
        val contentType: String,
        val bodyBase64: String,
        val canonical: String,
        val signature: String,
    )
}

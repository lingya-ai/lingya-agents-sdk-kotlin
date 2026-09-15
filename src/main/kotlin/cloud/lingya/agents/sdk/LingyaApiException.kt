package cloud.lingya.agents.sdk

/** HTTP error returned by the Lingya Agents API. */
public class LingyaApiException(
    public val statusCode: Int,
    public val responseBody: String?,
) : RuntimeException("Lingya Agents API returned HTTP $statusCode")

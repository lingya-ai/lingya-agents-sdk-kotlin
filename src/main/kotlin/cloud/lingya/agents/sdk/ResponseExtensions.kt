package cloud.lingya.agents.sdk

import retrofit2.Response

/** Returns a successful response body or throws [LingyaApiException]. */
public fun <T : Any> Response<T>.bodyOrThrow(): T {
    if (!isSuccessful) throw LingyaApiException(code(), errorBody()?.string())
    return body() ?: throw LingyaApiException(code(), "Successful response did not contain a body")
}

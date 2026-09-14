package ai.lingya.agents.sdk.generated.api

import ai.lingya.agents.sdk.generated.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.fasterxml.jackson.annotation.JsonProperty

import ai.lingya.agents.sdk.generated.model.AgentsConfig
import ai.lingya.agents.sdk.generated.model.CodeMessage
import ai.lingya.agents.sdk.generated.model.ConversationConfig
import ai.lingya.agents.sdk.generated.model.ValidationError

interface ConfigurationApi {
    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/config
     * 读取 Agent 配置 / Get Agent configuration
     * 读取 Agent 配置 / Get Agent configuration 请求会在身份验签和资源归属校验后执行；响应字段以本契约为准。 / The request runs after signature and resource-ownership validation; this contract defines the response fields.
     * Responses:
     *  - 200: 读取 Agent 配置 / Get Agent configuration 的成功响应。 / Successful response for getAgentsConfig.
     *  - 401: API error
     *  - 403: API error
     *  - 413: API error
     *  - 422: Validation error
     *  - 429: API error
     *  - 500: API error
     *  - 503: API error
     *
     * @param channelId Agent OpenAPI 渠道 UUID。 / Agent OpenAPI channel UUID.
     * @return [AgentsConfig]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/config")
    suspend fun getAgentsConfig(@Path("channelId") channelId: kotlin.String): Response<AgentsConfig>

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/config
     * 读取会话配置 / Get conversation configuration
     * 读取会话配置 / Get conversation configuration 请求会在身份验签和资源归属校验后执行；响应字段以本契约为准。 / The request runs after signature and resource-ownership validation; this contract defines the response fields.
     * Responses:
     *  - 200: 读取会话配置 / Get conversation configuration 的成功响应。 / Successful response for getConversationConfig.
     *  - 401: API error
     *  - 403: API error
     *  - 413: API error
     *  - 422: Validation error
     *  - 429: API error
     *  - 500: API error
     *  - 503: API error
     *
     * @param channelId Agent OpenAPI 渠道 UUID。 / Agent OpenAPI channel UUID.
     * @param conversationId 会话 ID；必须属于当前外部用户。 / Conversation ID owned by the current external user.
     * @return [ConversationConfig]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/config")
    suspend fun getConversationConfig(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String): Response<ConversationConfig>

}

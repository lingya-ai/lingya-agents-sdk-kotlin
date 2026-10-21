package cloud.lingya.agents.sdk.generated.api

import cloud.lingya.agents.sdk.generated.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.fasterxml.jackson.annotation.JsonProperty

import cloud.lingya.agents.sdk.generated.model.AgentsConfig
import cloud.lingya.agents.sdk.generated.model.CodeMessage
import cloud.lingya.agents.sdk.generated.model.ConversationConfig
import cloud.lingya.agents.sdk.generated.model.OpenApiToken
import cloud.lingya.agents.sdk.generated.model.ValidationError

interface ConfigurationApi {
    /**
     * POST api/agents/channel/openapi/v1/{channelId}/chat/presign
     * 签发一次性短期渠道令牌 / Create a single-use short-lived channel token
     * ### 使用场景 仅接受 AK/SK 签名且请求体必须为空；返回 token 和 expiresAt。十分钟内有效的一次性渠道令牌，绑定签发时的渠道和用户身份。截止 expiresAt 前仅能认证一个新请求；认证成功即消费，业务失败不退还。每次新请求、重试或 SSE/WebSocket 重连都必须重新签发，不得缓存复用。使用 X-OpenAPI-Token，与所有 HMAC 鉴权头互斥，不能用于 presign。  ### Use case Requires AK/SK signing with an empty request body; returns token and expiresAt. A single-use channel token valid for ten minutes, bound to the issuing channel and user. It authenticates only one new request before expiresAt and is consumed on successful authentication even if the business operation fails. Obtain a fresh token for every new request, retry, or SSE/WebSocket reconnection; never cache or reuse it. Send X-OpenAPI-Token without any HMAC authentication headers; tokens cannot call presign.  ### 前置条件 有效渠道身份；会话资源必须属于当前用户。  ### Prerequisites Valid channel identity; conversation resources must belong to the current user.  ### 行为与副作用 仅接受 AK/SK 签名且请求体必须为空；返回 token 和 expiresAt。十分钟内有效的一次性渠道令牌，绑定签发时的渠道和用户身份。截止 expiresAt 前仅能认证一个新请求；认证成功即消费，业务失败不退还。每次新请求、重试或 SSE/WebSocket 重连都必须重新签发，不得缓存复用。使用 X-OpenAPI-Token，与所有 HMAC 鉴权头互斥，不能用于 presign。  ### Behavior and side effects Requires AK/SK signing with an empty request body; returns token and expiresAt. A single-use channel token valid for ten minutes, bound to the issuing channel and user. It authenticates only one new request before expiresAt and is consumed on successful authentication even if the business operation fails. Obtain a fresh token for every new request, retry, or SSE/WebSocket reconnection; never cache or reuse it. Send X-OpenAPI-Token without any HMAC authentication headers; tokens cannot call presign.  ### 后续调用 根据返回的权威状态更新客户端。  ### Next step Update the client using the returned authoritative state.  ### 接口摘要 签发一次性短期渠道令牌 / Create a single-use short-lived channel token  ### Operation summary 签发一次性短期渠道令牌 / Create a single-use short-lived channel token
     * Responses:
     *  - 201: 签发一次性短期渠道令牌 / Create a single-use short-lived channel token 的成功响应。 / Successful response for createOpenApiToken.
     *  - 401: 接口错误。 / API error.
     *  - 403: 接口错误。 / API error.
     *  - 413: 接口错误。 / API error.
     *  - 422: 参数校验错误。 / Validation error.
     *  - 429: 接口错误。 / API error.
     *  - 500: 接口错误。 / API error.
     *  - 503: 接口错误。 / API error.
     *
     * @param channelId Agent OpenAPI 渠道 UUID。 / Agent OpenAPI channel UUID.
     * @return [OpenApiToken]
     */
    @POST("api/agents/channel/openapi/v1/{channelId}/chat/presign")
    suspend fun createOpenApiToken(@Path("channelId") channelId: kotlin.String): Response<OpenApiToken>

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/config
     * 读取 Agent 配置 / Get Agent configuration
     * ### 使用场景 在初始化客户端能力或展示模型选择器前读取渠道配置。  ### Use case Read channel configuration before initializing client capabilities or showing model choices.  ### 前置条件 仅需有效渠道凭证。  ### Prerequisites Only valid channel credentials are required.  ### 行为与副作用 只读，不修改配置。  ### Behavior and side effects Read-only; no configuration is changed.  ### 后续调用 按附件限制准备文件，或创建会话。  ### Next step Prepare files according to the limits or create a conversation.  ### 接口摘要 读取 Agent 配置 / Get Agent configuration  ### Operation summary 读取 Agent 配置 / Get Agent configuration
     * Responses:
     *  - 200: 读取 Agent 配置 / Get Agent configuration 的成功响应。 / Successful response for getAgentsConfig.
     *  - 401: 接口错误。 / API error.
     *  - 403: 接口错误。 / API error.
     *  - 413: 接口错误。 / API error.
     *  - 422: 参数校验错误。 / Validation error.
     *  - 429: 接口错误。 / API error.
     *  - 500: 接口错误。 / API error.
     *  - 503: 接口错误。 / API error.
     *
     * @param channelId Agent OpenAPI 渠道 UUID。 / Agent OpenAPI channel UUID.
     * @return [AgentsConfig]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/config")
    suspend fun getAgentsConfig(@Path("channelId") channelId: kotlin.String): Response<AgentsConfig>

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/config
     * 读取会话配置 / Get conversation configuration
     * ### 使用场景 恢复会话时读取该会话最近使用的模型配置。  ### Use case Read the most recently used model configuration when resuming a conversation.  ### 前置条件 渠道凭证和外部用户已配置，目标资源属于当前用户。  ### Prerequisites Channel credentials and the external user are configured, and the target resource belongs to that user.  ### 行为与副作用 只读，不修改会话。  ### Behavior and side effects Read-only; the conversation is unchanged.  ### 后续调用 继续提交消息或展示当前模型。  ### Next step Submit another message or display the current model.  ### 接口摘要 读取会话配置 / Get conversation configuration  ### Operation summary 读取会话配置 / Get conversation configuration
     * Responses:
     *  - 200: 读取会话配置 / Get conversation configuration 的成功响应。 / Successful response for getConversationConfig.
     *  - 401: 接口错误。 / API error.
     *  - 403: 接口错误。 / API error.
     *  - 413: 接口错误。 / API error.
     *  - 422: 参数校验错误。 / Validation error.
     *  - 429: 接口错误。 / API error.
     *  - 500: 接口错误。 / API error.
     *  - 503: 接口错误。 / API error.
     *
     * @param channelId Agent OpenAPI 渠道 UUID。 / Agent OpenAPI channel UUID.
     * @param conversationId 会话 ID；必须属于当前外部用户。 / Conversation ID owned by the current external user.
     * @return [ConversationConfig]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/config")
    suspend fun getConversationConfig(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String): Response<ConversationConfig>

}

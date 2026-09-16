package cloud.lingya.agents.sdk.generated.api

import cloud.lingya.agents.sdk.generated.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.fasterxml.jackson.annotation.JsonProperty

import cloud.lingya.agents.sdk.generated.model.AgentsConfig
import cloud.lingya.agents.sdk.generated.model.CodeMessage
import cloud.lingya.agents.sdk.generated.model.ConversationConfig
import cloud.lingya.agents.sdk.generated.model.ValidationError

interface ConfigurationApi {
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

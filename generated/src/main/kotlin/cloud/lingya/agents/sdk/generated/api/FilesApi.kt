package cloud.lingya.agents.sdk.generated.api

import cloud.lingya.agents.sdk.generated.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.fasterxml.jackson.annotation.JsonProperty

import cloud.lingya.agents.sdk.generated.model.AgentFile
import cloud.lingya.agents.sdk.generated.model.CodeMessage
import cloud.lingya.agents.sdk.generated.model.ConfirmUploadInput
import cloud.lingya.agents.sdk.generated.model.CreateFileInput
import cloud.lingya.agents.sdk.generated.model.FileExists
import cloud.lingya.agents.sdk.generated.model.GeneratePreSignedUrlInput
import cloud.lingya.agents.sdk.generated.model.GeneratePreSignedUrlOutput
import cloud.lingya.agents.sdk.generated.model.PreSignedReadUrl
import cloud.lingya.agents.sdk.generated.model.ValidationError

interface FilesApi {
    /**
     * POST api/agents/channel/openapi/v1/{channelId}/chat/files/pre-signed-url/confirm
     * 确认预签名上传 / Confirm a presigned upload
     * 确认预签名上传 / Confirm a presigned upload 请求会在身份验签和资源归属校验后执行；响应字段以本契约为准。 / The request runs after signature and resource-ownership validation; this contract defines the response fields.
     * Responses:
     *  - 201: 确认预签名上传 / Confirm a presigned upload 的成功响应。 / Successful response for confirmPreSignedUpload.
     *  - 401: API error
     *  - 403: API error
     *  - 413: API error
     *  - 422: Validation error
     *  - 429: API error
     *  - 500: API error
     *  - 503: API error
     *
     * @param channelId Agent OpenAPI 渠道 UUID。 / Agent OpenAPI channel UUID.
     * @param confirmUploadInput 确认预签名上传 / Confirm a presigned upload 的 JSON 请求参数。 / JSON request parameters for confirmPreSignedUpload.
     * @return [AgentFile]
     */
    @POST("api/agents/channel/openapi/v1/{channelId}/chat/files/pre-signed-url/confirm")
    suspend fun confirmPreSignedUpload(@Path("channelId") channelId: kotlin.String, @Body confirmUploadInput: ConfirmUploadInput): Response<AgentFile>

    /**
     * POST api/agents/channel/openapi/v1/{channelId}/chat/files/contentMd5
     * 按 MD5 复用文件 / Reuse a file by MD5
     * 按 MD5 复用文件 / Reuse a file by MD5 请求会在身份验签和资源归属校验后执行；响应字段以本契约为准。 / The request runs after signature and resource-ownership validation; this contract defines the response fields.
     * Responses:
     *  - 201: 按 MD5 复用文件 / Reuse a file by MD5 的成功响应。 / Successful response for createFileByContentMd5.
     *  - 401: API error
     *  - 403: API error
     *  - 413: API error
     *  - 422: Validation error
     *  - 429: API error
     *  - 500: API error
     *  - 503: API error
     *
     * @param channelId Agent OpenAPI 渠道 UUID。 / Agent OpenAPI channel UUID.
     * @param createFileInput 按 MD5 复用文件 / Reuse a file by MD5 的 JSON 请求参数。 / JSON request parameters for createFileByContentMd5.
     * @return [AgentFile]
     */
    @POST("api/agents/channel/openapi/v1/{channelId}/chat/files/contentMd5")
    suspend fun createFileByContentMd5(@Path("channelId") channelId: kotlin.String, @Body createFileInput: CreateFileInput): Response<AgentFile>

    /**
     * POST api/agents/channel/openapi/v1/{channelId}/chat/files/pre-signed-url/write
     * 创建预签名上传地址 / Create a presigned upload URL
     * 创建预签名上传地址 / Create a presigned upload URL 请求会在身份验签和资源归属校验后执行；响应字段以本契约为准。 / The request runs after signature and resource-ownership validation; this contract defines the response fields.
     * Responses:
     *  - 201: 创建预签名上传地址 / Create a presigned upload URL 的成功响应。 / Successful response for createPreSignedUpload.
     *  - 401: API error
     *  - 403: API error
     *  - 413: API error
     *  - 422: Validation error
     *  - 429: API error
     *  - 500: API error
     *  - 503: API error
     *
     * @param channelId Agent OpenAPI 渠道 UUID。 / Agent OpenAPI channel UUID.
     * @param generatePreSignedUrlInput 创建预签名上传地址 / Create a presigned upload URL 的 JSON 请求参数。 / JSON request parameters for createPreSignedUpload.
     * @return [GeneratePreSignedUrlOutput]
     */
    @POST("api/agents/channel/openapi/v1/{channelId}/chat/files/pre-signed-url/write")
    suspend fun createPreSignedUpload(@Path("channelId") channelId: kotlin.String, @Body generatePreSignedUrlInput: GeneratePreSignedUrlInput): Response<GeneratePreSignedUrlOutput>

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/files/meta/contentMd5
     * 检查 MD5 文件是否存在 / Check file existence by MD5
     * 检查 MD5 文件是否存在 / Check file existence by MD5 请求会在身份验签和资源归属校验后执行；响应字段以本契约为准。 / The request runs after signature and resource-ownership validation; this contract defines the response fields.
     * Responses:
     *  - 200: 检查 MD5 文件是否存在 / Check file existence by MD5 的成功响应。 / Successful response for fileExistsByContentMd5.
     *  - 401: API error
     *  - 403: API error
     *  - 413: API error
     *  - 422: Validation error
     *  - 429: API error
     *  - 500: API error
     *  - 503: API error
     *
     * @param channelId Agent OpenAPI 渠道 UUID。 / Agent OpenAPI channel UUID.
     * @param contentMd5 文件内容 MD5。 / MD5 digest of the file content.
     * @return [FileExists]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/files/meta/contentMd5")
    suspend fun fileExistsByContentMd5(@Path("channelId") channelId: kotlin.String, @Query("contentMd5") contentMd5: kotlin.String): Response<FileExists>

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/files/{fileId}/preview
     * 创建会话文件预览地址 / Create a conversation file preview URL
     * 创建会话文件预览地址 / Create a conversation file preview URL 请求会在身份验签和资源归属校验后执行；响应字段以本契约为准。 / The request runs after signature and resource-ownership validation; this contract defines the response fields.
     * Responses:
     *  - 200: 创建会话文件预览地址 / Create a conversation file preview URL 的成功响应。 / Successful response for getConversationFilePreview.
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
     * @param fileId 文件记录 ID。 / File record ID.
     * @return [PreSignedReadUrl]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/files/{fileId}/preview")
    suspend fun getConversationFilePreview(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Path("fileId") fileId: kotlin.Long): Response<PreSignedReadUrl>

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/messages/{messageId}/plan-intermediate-files/{fileId}/preview
     * 创建计划快照预览地址 / Create a plan snapshot preview URL
     * 创建计划快照预览地址 / Create a plan snapshot preview URL 请求会在身份验签和资源归属校验后执行；响应字段以本契约为准。 / The request runs after signature and resource-ownership validation; this contract defines the response fields.
     * Responses:
     *  - 200: 创建计划快照预览地址 / Create a plan snapshot preview URL 的成功响应。 / Successful response for getPlanIntermediateFilePreview.
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
     * @param messageId 用户消息 ID；必须属于指定会话。 / User-message ID owned by the specified conversation.
     * @param fileId 文件记录 ID。 / File record ID.
     * @return [PreSignedReadUrl]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/messages/{messageId}/plan-intermediate-files/{fileId}/preview")
    suspend fun getPlanIntermediateFilePreview(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Path("messageId") messageId: kotlin.String, @Path("fileId") fileId: kotlin.Long): Response<PreSignedReadUrl>

}

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
     * ### 使用场景 对象存储上传成功后登记文件。  ### Use case Register a file after object-storage upload succeeds.  ### 前置条件 持有预签名响应的 fileUk 和同一 contentMd5。  ### Prerequisites The fileUk and matching contentMd5 from the presign response are available.  ### 行为与副作用 确认文件并返回可用于聊天的文件记录。  ### Behavior and side effects Confirms the file and returns a record usable in chat.  ### 后续调用 把返回的文件 ID 放入聊天请求。  ### Next step Place the returned file ID in a chat request.  ### 接口摘要 确认预签名上传 / Confirm a presigned upload  ### Operation summary 确认预签名上传 / Confirm a presigned upload
     * Responses:
     *  - 201: 确认预签名上传 / Confirm a presigned upload 的成功响应。 / Successful response for confirmPreSignedUpload.
     *  - 401: 接口错误。 / API error.
     *  - 403: 接口错误。 / API error.
     *  - 413: 接口错误。 / API error.
     *  - 422: 参数校验错误。 / Validation error.
     *  - 429: 接口错误。 / API error.
     *  - 500: 接口错误。 / API error.
     *  - 503: 接口错误。 / API error.
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
     * ### 使用场景 复用服务端已经存在的相同内容文件。  ### Use case Reuse an existing server-side file with identical content.  ### 前置条件 已计算 MD5 并确认内容可复用。  ### Prerequisites The MD5 is known and the content is safe to reuse.  ### 行为与副作用 创建当前用户可引用的文件记录，不上传字节。  ### Behavior and side effects Creates a user-visible file record without uploading bytes.  ### 后续调用 把返回的文件 ID 放入聊天请求。  ### Next step Place the returned file ID in a chat request.  ### 接口摘要 按 MD5 复用文件 / Reuse a file by MD5  ### Operation summary 按 MD5 复用文件 / Reuse a file by MD5
     * Responses:
     *  - 201: 按 MD5 复用文件 / Reuse a file by MD5 的成功响应。 / Successful response for createFileByContentMd5.
     *  - 401: 接口错误。 / API error.
     *  - 403: 接口错误。 / API error.
     *  - 413: 接口错误。 / API error.
     *  - 422: 参数校验错误。 / Validation error.
     *  - 429: 接口错误。 / API error.
     *  - 500: 接口错误。 / API error.
     *  - 503: 接口错误。 / API error.
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
     * ### 使用场景 为需要上传的新附件申请对象存储地址。  ### Use case Request an object-storage URL for a new attachment.  ### 前置条件 已计算文件 MD5，文件模块为 ai-chat-attachments。  ### Prerequisites The file MD5 is known and the module is ai-chat-attachments.  ### 行为与副作用 创建短期上传授权，不代表上传完成。  ### Behavior and side effects Creates short-lived upload authorization; upload is not yet complete.  ### 后续调用 按返回 headers 上传文件，再调用确认接口。  ### Next step Upload with the returned headers, then call confirmation.  ### 接口摘要 创建预签名上传地址 / Create a presigned upload URL  ### Operation summary 创建预签名上传地址 / Create a presigned upload URL
     * Responses:
     *  - 201: 创建预签名上传地址 / Create a presigned upload URL 的成功响应。 / Successful response for createPreSignedUpload.
     *  - 401: 接口错误。 / API error.
     *  - 403: 接口错误。 / API error.
     *  - 413: 接口错误。 / API error.
     *  - 422: 参数校验错误。 / Validation error.
     *  - 429: 接口错误。 / API error.
     *  - 500: 接口错误。 / API error.
     *  - 503: 接口错误。 / API error.
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
     * ### 使用场景 上传前检查相同内容是否已经存在。  ### Use case Check whether identical content exists before uploading.  ### 前置条件 已计算文件 MD5。  ### Prerequisites The file MD5 is known.  ### 行为与副作用 只读。  ### Behavior and side effects Read-only.  ### 后续调用 存在时尝试复用，否则走预签名上传。  ### Next step Attempt reuse when present; otherwise use presigned upload.  ### 接口摘要 检查 MD5 文件是否存在 / Check file existence by MD5  ### Operation summary 检查 MD5 文件是否存在 / Check file existence by MD5
     * Responses:
     *  - 200: 检查 MD5 文件是否存在 / Check file existence by MD5 的成功响应。 / Successful response for fileExistsByContentMd5.
     *  - 401: 接口错误。 / API error.
     *  - 403: 接口错误。 / API error.
     *  - 413: 接口错误。 / API error.
     *  - 422: 参数校验错误。 / Validation error.
     *  - 429: 接口错误。 / API error.
     *  - 500: 接口错误。 / API error.
     *  - 503: 接口错误。 / API error.
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
     * ### 使用场景 为会话附件创建短期预览地址。  ### Use case Create a short-lived preview URL for a conversation attachment.  ### 前置条件 渠道凭证和外部用户已配置，目标资源属于当前用户。  ### Prerequisites Channel credentials and the external user are configured, and the target resource belongs to that user.  ### 行为与副作用 生成临时读取授权，不修改文件。  ### Behavior and side effects Creates temporary read authorization without changing the file.  ### 后续调用 在过期前打开或下载 URL。  ### Next step Open or download the URL before it expires.  ### 接口摘要 创建会话文件预览地址 / Create a conversation file preview URL  ### Operation summary 创建会话文件预览地址 / Create a conversation file preview URL
     * Responses:
     *  - 200: 创建会话文件预览地址 / Create a conversation file preview URL 的成功响应。 / Successful response for getConversationFilePreview.
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
     * @param fileId 文件记录 ID。 / File record ID.
     * @return [PreSignedReadUrl]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/files/{fileId}/preview")
    suspend fun getConversationFilePreview(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Path("fileId") fileId: kotlin.Long): Response<PreSignedReadUrl>

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/messages/{messageId}/plan-intermediate-files/{fileId}/preview
     * 创建计划快照预览地址 / Create a plan snapshot preview URL
     * ### 使用场景 预览计划执行过程中生成的中间文件。  ### Use case Preview an intermediate file produced during plan execution.  ### 前置条件 渠道凭证和外部用户已配置，目标资源属于当前用户。  ### Prerequisites Channel credentials and the external user are configured, and the target resource belongs to that user.  ### 行为与副作用 生成临时读取授权。  ### Behavior and side effects Creates temporary read authorization.  ### 后续调用 在过期前使用 URL，并保留消息上下文。  ### Next step Use the URL before expiry and preserve message context.  ### 接口摘要 创建计划快照预览地址 / Create a plan snapshot preview URL  ### Operation summary 创建计划快照预览地址 / Create a plan snapshot preview URL
     * Responses:
     *  - 200: 创建计划快照预览地址 / Create a plan snapshot preview URL 的成功响应。 / Successful response for getPlanIntermediateFilePreview.
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
     * @param messageId 用户消息 ID；必须属于指定会话。 / User-message ID owned by the specified conversation.
     * @param fileId 文件记录 ID。 / File record ID.
     * @return [PreSignedReadUrl]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/messages/{messageId}/plan-intermediate-files/{fileId}/preview")
    suspend fun getPlanIntermediateFilePreview(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Path("messageId") messageId: kotlin.String, @Path("fileId") fileId: kotlin.Long): Response<PreSignedReadUrl>

}

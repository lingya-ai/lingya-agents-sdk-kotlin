package ai.lingya.agents.sdk.generated.api

import ai.lingya.agents.sdk.generated.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.fasterxml.jackson.annotation.JsonProperty

import ai.lingya.agents.sdk.generated.model.AgentFile
import ai.lingya.agents.sdk.generated.model.CodeMessage
import ai.lingya.agents.sdk.generated.model.ConfirmUploadInput
import ai.lingya.agents.sdk.generated.model.CreateFileInput
import ai.lingya.agents.sdk.generated.model.FileExists
import ai.lingya.agents.sdk.generated.model.GeneratePreSignedUrlInput
import ai.lingya.agents.sdk.generated.model.GeneratePreSignedUrlOutput
import ai.lingya.agents.sdk.generated.model.PreSignedReadUrl
import ai.lingya.agents.sdk.generated.model.ValidationError

interface FilesApi {
    /**
     * POST api/agents/channel/openapi/v1/{channelId}/chat/files/pre-signed-url/confirm
     * confirmPreSignedUpload
     * 
     * Responses:
     *  - 201: Successful response
     *  - 401: API error
     *  - 403: API error
     *  - 413: API error
     *  - 422: Validation error
     *  - 429: API error
     *  - 500: API error
     *  - 503: API error
     *
     * @param channelId 
     * @param confirmUploadInput 
     * @return [AgentFile]
     */
    @POST("api/agents/channel/openapi/v1/{channelId}/chat/files/pre-signed-url/confirm")
    suspend fun confirmPreSignedUpload(@Path("channelId") channelId: kotlin.String, @Body confirmUploadInput: ConfirmUploadInput): Response<AgentFile>

    /**
     * POST api/agents/channel/openapi/v1/{channelId}/chat/files/contentMd5
     * createFileByContentMd5
     * 
     * Responses:
     *  - 201: Successful response
     *  - 401: API error
     *  - 403: API error
     *  - 413: API error
     *  - 422: Validation error
     *  - 429: API error
     *  - 500: API error
     *  - 503: API error
     *
     * @param channelId 
     * @param createFileInput 
     * @return [AgentFile]
     */
    @POST("api/agents/channel/openapi/v1/{channelId}/chat/files/contentMd5")
    suspend fun createFileByContentMd5(@Path("channelId") channelId: kotlin.String, @Body createFileInput: CreateFileInput): Response<AgentFile>

    /**
     * POST api/agents/channel/openapi/v1/{channelId}/chat/files/pre-signed-url/write
     * createPreSignedUpload
     * 
     * Responses:
     *  - 201: Successful response
     *  - 401: API error
     *  - 403: API error
     *  - 413: API error
     *  - 422: Validation error
     *  - 429: API error
     *  - 500: API error
     *  - 503: API error
     *
     * @param channelId 
     * @param generatePreSignedUrlInput 
     * @return [GeneratePreSignedUrlOutput]
     */
    @POST("api/agents/channel/openapi/v1/{channelId}/chat/files/pre-signed-url/write")
    suspend fun createPreSignedUpload(@Path("channelId") channelId: kotlin.String, @Body generatePreSignedUrlInput: GeneratePreSignedUrlInput): Response<GeneratePreSignedUrlOutput>

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/files/meta/contentMd5
     * fileExistsByContentMd5
     * 
     * Responses:
     *  - 200: Successful response
     *  - 401: API error
     *  - 403: API error
     *  - 413: API error
     *  - 422: Validation error
     *  - 429: API error
     *  - 500: API error
     *  - 503: API error
     *
     * @param channelId 
     * @param contentMd5 
     * @return [FileExists]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/files/meta/contentMd5")
    suspend fun fileExistsByContentMd5(@Path("channelId") channelId: kotlin.String, @Query("contentMd5") contentMd5: kotlin.String): Response<FileExists>

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/files/{fileId}/preview
     * getConversationFilePreview
     * 
     * Responses:
     *  - 200: Successful response
     *  - 401: API error
     *  - 403: API error
     *  - 413: API error
     *  - 422: Validation error
     *  - 429: API error
     *  - 500: API error
     *  - 503: API error
     *
     * @param channelId 
     * @param conversationId 
     * @param fileId 
     * @return [PreSignedReadUrl]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/files/{fileId}/preview")
    suspend fun getConversationFilePreview(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Path("fileId") fileId: kotlin.Long): Response<PreSignedReadUrl>

    /**
     * GET api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/messages/{messageId}/plan-intermediate-files/{fileId}/preview
     * getPlanIntermediateFilePreview
     * 
     * Responses:
     *  - 200: Successful response
     *  - 401: API error
     *  - 403: API error
     *  - 413: API error
     *  - 422: Validation error
     *  - 429: API error
     *  - 500: API error
     *  - 503: API error
     *
     * @param channelId 
     * @param conversationId 
     * @param messageId 
     * @param fileId 
     * @return [PreSignedReadUrl]
     */
    @GET("api/agents/channel/openapi/v1/{channelId}/chat/conversations/{conversationId}/messages/{messageId}/plan-intermediate-files/{fileId}/preview")
    suspend fun getPlanIntermediateFilePreview(@Path("channelId") channelId: kotlin.String, @Path("conversationId") conversationId: kotlin.String, @Path("messageId") messageId: kotlin.String, @Path("fileId") fileId: kotlin.Long): Response<PreSignedReadUrl>

}

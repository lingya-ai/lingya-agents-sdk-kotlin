package ai.lingya.agents.sdk

import ai.lingya.agents.sdk.generated.api.ChatApi
import ai.lingya.agents.sdk.generated.api.ConfigurationApi
import ai.lingya.agents.sdk.generated.api.ConversationsApi
import ai.lingya.agents.sdk.generated.api.EventsApi
import ai.lingya.agents.sdk.generated.api.FilesApi
import ai.lingya.agents.sdk.generated.api.InteractionsApi
import ai.lingya.agents.sdk.generated.api.KnowledgeApi
import ai.lingya.agents.sdk.generated.api.MessagesApi
import ai.lingya.agents.sdk.generated.api.SQLApi
import ai.lingya.agents.sdk.generated.api.WorkspaceApi
import retrofit2.Retrofit

/** Typed generated API groups bound to one external user. */
public class LingyaAgentsApis internal constructor(retrofit: Retrofit) {
    public val chat: ChatApi = retrofit.create(ChatApi::class.java)
    public val configuration: ConfigurationApi = retrofit.create(ConfigurationApi::class.java)
    public val conversations: ConversationsApi = retrofit.create(ConversationsApi::class.java)
    public val events: EventsApi = retrofit.create(EventsApi::class.java)
    public val files: FilesApi = retrofit.create(FilesApi::class.java)
    public val interactions: InteractionsApi = retrofit.create(InteractionsApi::class.java)
    public val knowledge: KnowledgeApi = retrofit.create(KnowledgeApi::class.java)
    public val messages: MessagesApi = retrofit.create(MessagesApi::class.java)
    public val sql: SQLApi = retrofit.create(SQLApi::class.java)
    public val workspace: WorkspaceApi = retrofit.create(WorkspaceApi::class.java)
}

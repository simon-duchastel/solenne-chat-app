package com.duchastel.simon.solenne.data.chat

import com.duchastel.simon.solenne.db.chat.ChatMessageDb
import com.duchastel.simon.solenne.db.chat.DbMessage
import dev.zacsweers.metro.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class ChatMessageRepositoryImpl @Inject constructor(
    private val chatMessageDb: ChatMessageDb,
) : ChatMessageRepository {
    override fun getMessageFlowForConversation(conversationId: String): Flow<List<ChatMessage>> {
        return chatMessageDb.getMessagesForConversation(conversationId)
            .distinctUntilChanged()
            .map { query ->
                query.map(DbMessage::toChatMessage)
            }
    }

    @OptIn(ExperimentalUuidApi::class, ExperimentalTime::class)
    override suspend fun addMessageToConversation(
        conversationId: String,
        author: MessageAuthor,
        text: String,
    ) {
        withContext(Dispatchers.Default) {
            chatMessageDb.writeMessage(
                DbMessage(
                    id = Uuid.random().toHexString(),
                    conversationId = conversationId,
                    author = when (author) {
                        MessageAuthor.User -> 0L
                        MessageAuthor.AI -> 1L
                    },
                    content = text.trim(),
                    timestamp = Clock.System.now().toEpochMilliseconds(),
                )
            )
        }
    }
}

fun DbMessage.toChatMessage(): ChatMessage {
    return ChatMessage(
        id = id,
        text = content,
        author = when (author) {
            0L -> MessageAuthor.User
            1L -> MessageAuthor.AI
            else -> error("Unknown author received for GetMessagesForConversation[$this] - $author")
        }
    )
}
package com.duchastel.simon.solenne.data.chat


import com.duchastel.simon.solenne.localstorage.chat.LocalStorageChatMessage
import dev.zacsweers.metro.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface ChatMessageRepository {
    fun getMessagesForConversation(conversationId: String): Flow<List<ChatMessage>>
}

class ChatMessageRepositoryImpl @Inject constructor(
//    private val chatMessageLocalStorage: ChatMessageLocalStorage,
): ChatMessageRepository {

    override fun getMessagesForConversation(conversationId: String): Flow<List<ChatMessage>> {
        return flow {
//            val storedMessages = chatMessageLocalStorage.getMessagesForConversation(conversationId)
//            emit(storedMessages.map { it.toChatMessage() })
        }
    }
}

private fun LocalStorageChatMessage.toChatMessage(): ChatMessage {
    return ChatMessage(
        id = id,
        text = content,
        author = when (author) {
            LocalStorageChatMessage.Author.User -> MessageAuthor.User
            LocalStorageChatMessage.Author.AI -> MessageAuthor.AI
        },
    )
}
package com.duchastel.simon.solenne.localstorage.chat

interface ChatMessageLocalStorage {
    suspend fun getMessagesForConversation(conversationId: String): List<LocalStorageChatMessage>
}

data class LocalStorageChatMessage(
    val id: String,
    val conversationId: String,
    val author: Author,
    val content: String,
    val timestamp: Long,
) {
    sealed class Author {
        data object User : Author()
        data object AI : Author()
    }
}
package com.duchastel.simon.solenne.data.chat

import app.cash.sqldelight.async.coroutines.awaitAsList
import app.cash.sqldelight.coroutines.asFlow
import com.duchastel.simon.solenne.Database
import com.duchastel.simon.solenne.chat.data.GetMessagesForConversation
import com.duchastel.simon.solenne.dao.toChatMessage
import dev.zacsweers.metro.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

interface ChatMessageRepository {
    fun getMessagesForConversation(conversationId: String): Flow<List<ChatMessage>>
}

class ChatMessageRepositoryImpl @Inject constructor(
    database: Database,
): ChatMessageRepository {
    private val chatMessageQueries = try {
        database.chatMessageQueries
    } catch (ex: Exception) {
        println("Error initializing chat message queries: ${ex.message}")
        null
    }

    override fun getMessagesForConversation(conversationId: String): Flow<List<ChatMessage>> {
        return try {
            chatMessageQueries ?: return flowOf()
            chatMessageQueries.getMessagesForConversation(conversationId)
                .asFlow()
                .distinctUntilChanged()
                .map { query ->
                    query.awaitAsList().map(GetMessagesForConversation::toChatMessage)
                }
        } catch (ex: Exception) {
            println("Error fetching messages: ${ex.message}")
            flowOf()
        }
    }
}
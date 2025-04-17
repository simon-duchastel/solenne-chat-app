package com.duchastel.simon.solenne.dao

import com.duchastel.simon.solenne.chat.data.GetMessagesForConversation
import com.duchastel.simon.solenne.data.chat.ChatMessage
import com.duchastel.simon.solenne.data.chat.MessageAuthor

fun GetMessagesForConversation.toChatMessage(): ChatMessage {
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
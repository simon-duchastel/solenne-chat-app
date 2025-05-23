package com.duchastel.simon.solenne.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.duchastel.simon.solenne.data.chat.models.MessageAuthor
import com.duchastel.simon.solenne.fakes.ChatMessagesFake
import com.duchastel.simon.solenne.ui.model.UIChatMessage
import com.duchastel.simon.solenne.ui.model.toUIChatMessage
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ChatMessage(
    message: UIChatMessage,
    modifier: Modifier = Modifier,
) {
    val bubbleColor = if (message.isUser) Color(0xFFE1FFC7) else Color(0xFFF1F0F0)
    Row(
        modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        horizontalArrangement = if (message.isUser) Arrangement.End else Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.90f)
                .background(
                    color = bubbleColor,
                    shape = RoundedCornerShape(14.dp)
                )
                .padding(12.dp)
        ) {
            Text(text = message.text)
        }
    }
}

@Suppress("unused")
@Preview
@Composable
fun UserChatMessage_Preview() {
    ChatMessage(
        message = ChatMessagesFake.chatMessages.first { it.author is MessageAuthor.User }.toUIChatMessage(),
    )
}

@Suppress("unused")
@Preview
@Composable
fun LLMChatMessage_Preview() {
    ChatMessage(
        message = ChatMessagesFake.chatMessages.first { it.author is MessageAuthor.AI }.toUIChatMessage(),
    )
}
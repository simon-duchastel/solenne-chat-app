package com.duchastel.simon.solenne.data

import com.duchastel.simon.solenne.data.chat.ChatMessageRepositoryImpl
import com.duchastel.simon.solenne.data.chat.MessageAuthor
import com.duchastel.simon.solenne.db.chat.DbMessage
import com.duchastel.simon.solenne.fakes.FakeAiChatApi
import com.duchastel.simon.solenne.fakes.FakeChatMessageDb
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

internal class ChatMessageRepositoryImplTest {

    private lateinit var fakeDb: FakeChatMessageDb
    private lateinit var fakeAi: FakeAiChatApi

    private lateinit var repo: ChatMessageRepositoryImpl

    @BeforeTest
    fun setup() {
        initRepo()
    }

    private fun initRepo(
        initialDbMessages: Map<String, List<DbMessage>> = emptyMap(),
        geminiResponse: String = "Gemini response",
    ) {
        fakeDb = FakeChatMessageDb(initialDbMessages)
        fakeAi = FakeAiChatApi(fakeResponse = geminiResponse)
        repo = ChatMessageRepositoryImpl(
            chatMessageDb = fakeDb,
            geminiApi = fakeAi
        )
    }

    @Test
    fun `getMessagesForConversation - success`() = runTest {
        val conversationId = "conv-id"
        initRepo(
            initialDbMessages = mapOf(
                conversationId to listOf(
                    DbMessage(
                        id = "1",
                        conversationId = conversationId,
                        author = 0L,
                        content = "user-text",
                        timestamp = 123L
                    ),
                    DbMessage(
                        id = "2",
                        conversationId = conversationId,
                        author = 1L,
                        content = "ai-text",
                        timestamp = 456L,
                    )
                )
            )
        )

        val chats = repo.getMessagesForConversation(conversationId).first()
        assertEquals(2, chats.size)

        val first = chats[0]
        assertEquals("1", first.id)
        assertEquals("user-text", first.text)
        assertEquals(MessageAuthor.User, first.author)

        val second = chats[1]
        assertEquals("2", second.id)
        assertEquals("ai-text", second.text)
        assertEquals(MessageAuthor.AI, second.author)
    }

    @Test
    fun `sendTextToConversation - success`() = runTest {
        val conversationId = "conv-id"
        repo.sendTextToConversation(conversationId, "hello there")

        val dbMessages = fakeDb.getMessagesForConversation(conversationId).first()
        assertEquals(2, dbMessages.size)

        // First is the user message
        val userMsg = dbMessages[0]
        assertEquals(conversationId, userMsg.conversationId)
        assertEquals(0L, userMsg.author)
        assertEquals("hello there", userMsg.content)

        // Second is the AI response from our fake
        val aiMessage = dbMessages[1]
        assertEquals(conversationId, aiMessage.conversationId)
        assertEquals(1L, aiMessage.author)
        assertEquals("Gemini response", aiMessage.content)
    }
}


package com.duchastel.simon.solenne.data.chat

import com.duchastel.simon.solenne.data.chat.models.ChatConversation
import com.duchastel.simon.solenne.data.chat.models.ChatMessage
import com.duchastel.simon.solenne.data.chat.models.MessageAuthor
import com.duchastel.simon.solenne.data.tools.McpServerConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.json.JsonElement

/**
 * A repository for persisting and retrieving chat messages.
 *
 * Responsible for creating, storing, and retrieving all chat conversations.
 */
interface ChatMessageRepository {
    /**
     *  Returns a cold [Flow] that emits the list of available conversations.
     */
    fun getAvailableConversationsFlow(): Flow<List<ChatConversation>>

    /**
     *  Creates a new conversation and returns it if successful, null otherwise.
     */
    suspend fun createNewConversation(): ChatConversation?

    /**
     * Returns a cold [Flow] that emits the list of chat messages
     * in the conversation identified by [conversationId].
     *
     * @param conversationId the id of the conversation
     * @return a flow of the current list of [ChatMessage]s
     */
    fun getMessageFlowForConversation(conversationId: String): Flow<List<ChatMessage>>

    /**
     * Adds a new text message to the conversation.
     *
     * @param conversationId the id of the conversation
     * @param author the author of the message
     * @param text the plain‑text message
     * @return the added message if successful, null otherwise
     */
    suspend fun addTextMessageToConversation(
        conversationId: String,
        author: MessageAuthor,
        text: String,
    ): ChatMessage?

    /**
     * Adds a tool use request message to the conversation.
     *
     * @param conversationId the id of the conversation
     * @param mcpServer the server to use the tool on
     * @param toolName the name of the tool to use
     * @param argumentsSupplied the arguments supplied to the tool
     * @return the added message if successful, null otherwise
     */
    suspend fun addToolUseToConversation(
        conversationId: String,
        mcpServer: McpServerConfig,
        toolName: String,
        argumentsSupplied: Map<String, JsonElement>,
    ): ChatMessage?

    /**
     * Adds the result of a tool use to the conversation.
     *
     * @param conversationId the id of the conversation
     * @param toolResult the result of using the tool
     * @param messageId the id of the message to modify
     * @return the added message if successful, null otherwise
     */
    suspend fun addToolUseResultToConversation(
        conversationId: String,
        messageId: String,
        toolResult: ChatMessage.ToolUse.ToolResult,
    ): ChatMessage?

    /**
     * Modifies an existing message in the conversation.
     *
     * Useful for streaming conversations, where messages are
     * updated as they are received.
     *
     * @param conversationId the id of the conversation
     * @param messageId the id of the message to modify
     * @param updatedText the new text of the message
     * @return the modified message if successful, null otherwise
     */
    suspend fun modifyMessageFromConversation(
        conversationId: String,
        messageId: String,
        updatedText: String,
    ): ChatMessage?
}

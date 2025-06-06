package com.duchastel.simon.solenne.util.fakes

import com.duchastel.simon.solenne.data.ai.AIModelScope.GeminiModelScope
import com.duchastel.simon.solenne.network.ai.AiChatApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import com.duchastel.simon.solenne.network.ai.Conversation
import com.duchastel.simon.solenne.network.ai.ConversationResponse
import com.duchastel.simon.solenne.network.ai.NetworkMessage
import com.duchastel.simon.solenne.network.ai.Tool
import com.duchastel.simon.solenne.util.types.SolenneResult
import com.duchastel.simon.solenne.util.types.asSuccess

/**
 * A fake AiChatApi that always returns [fakeResponse], ignoring the request content.
 */
internal class FakeAiChatApi(
    private val fakeResponse: String = "fake-ai-response"
) : AiChatApi<GeminiModelScope> {

    override fun generateStreamingResponseForConversation(
        scope: GeminiModelScope,
        conversation: Conversation,
        systemPrompt: String?,
        tools: List<Tool>
    ): Flow<SolenneResult<ConversationResponse>> {
        return flowOf(
            ConversationResponse(
                newMessages = listOf(
                    NetworkMessage.AiNetworkMessage.Text(fakeResponse)
                )
            ).asSuccess()
        )
    }

    override suspend fun generateResponseForConversation(
        scope: GeminiModelScope,
        conversation: Conversation,
        systemPrompt: String?,
        tools: List<Tool>
    ): SolenneResult<ConversationResponse> {
        return ConversationResponse(
            newMessages = listOf(
                NetworkMessage.AiNetworkMessage.Text(fakeResponse)
            )
        ).asSuccess()
    }
}
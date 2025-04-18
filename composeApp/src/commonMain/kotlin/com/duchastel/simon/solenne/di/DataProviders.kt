package com.duchastel.simon.solenne.di

import com.duchastel.simon.solenne.data.ai.AiChatRepository
import com.duchastel.simon.solenne.data.ai.AiChatRepositoryImpl
import com.duchastel.simon.solenne.data.chat.ChatMessageRepository
import com.duchastel.simon.solenne.data.chat.ChatMessageRepositoryImpl
import dev.zacsweers.metro.Binds

interface DataProviders {
    @Binds
    fun ChatMessageRepositoryImpl.bind(): ChatMessageRepository

    @Binds
    fun AiChatRepositoryImpl.bind(): AiChatRepository
}
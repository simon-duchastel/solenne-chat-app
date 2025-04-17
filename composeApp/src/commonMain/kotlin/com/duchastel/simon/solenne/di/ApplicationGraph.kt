package com.duchastel.simon.solenne.di

import com.duchastel.simon.solenne.screens.chat.ChatPresenter
import com.duchastel.simon.solenne.screens.chat.ChatScreen
import com.duchastel.simon.solenne.screens.chat.ChatUi
import com.slack.circuit.foundation.Circuit
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.SingleIn

@SingleIn(AppScope::class)
interface ApplicationGraph: DataProviders {
    val circuit: Circuit

    @Provides
    fun provideCircuit(
        chatPresenterFactory: ChatPresenter.Factory
    ): Circuit {
        return Circuit.Builder()
            .addPresenter<ChatScreen, ChatScreen.State> { screen, _, _ ->
                chatPresenterFactory.create(screen)
            }
            .addUi<ChatScreen, ChatScreen.State> { state, modifier ->
                ChatUi(state, modifier)
            }
            .build()
    }
}
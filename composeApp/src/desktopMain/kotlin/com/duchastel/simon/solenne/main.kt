package com.duchastel.simon.solenne

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import dev.zacsweers.metro.createGraph
import com.duchastel.simon.solenne.di.ApplicationGraph

fun main() {
    val applicationGraph = createGraph<ApplicationGraph>()

    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "SolenneChatApp",
        ) {
            App(applicationGraph.circuit)
        }
    }
}
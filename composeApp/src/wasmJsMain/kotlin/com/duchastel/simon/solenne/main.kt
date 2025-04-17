package com.duchastel.simon.solenne

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.duchastel.simon.solenne.di.WasmJsApplicationGraph
import dev.zacsweers.metro.createGraph
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    val applicationGraph = createGraph<WasmJsApplicationGraph>()
    ComposeViewport(document.body!!) {
        App(applicationGraph.circuit)
    }
}
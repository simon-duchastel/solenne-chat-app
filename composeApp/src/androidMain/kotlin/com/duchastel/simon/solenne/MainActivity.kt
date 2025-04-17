package com.duchastel.simon.solenne

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.duchastel.simon.solenne.di.AndroidApplicationGraph
import dev.zacsweers.metro.createGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val applicationComponent = createGraph<AndroidApplicationGraph>()
        setContent {
            App(applicationComponent.circuit)
        }
    }
}

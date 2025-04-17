package com.duchastel.simon.solenne.di

import android.content.Context
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.SingleIn

@DependencyGraph(AppScope::class)
@SingleIn(AppScope::class)
interface AndroidApplicationGraph : ApplicationGraph {
    val applicationContext: Context

    @DependencyGraph.Factory
    fun interface Factory {
        fun create(@Provides applicationContext: Context): AndroidApplicationGraph
    }
}

package com.duchastel.simon.solenne.di

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.SingleIn

@DependencyGraph(AppScope::class)
@SingleIn(AppScope::class)
interface DesktopApplicationGraph : ApplicationGraph {

}

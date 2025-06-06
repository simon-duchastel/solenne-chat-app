package com.duchastel.simon.solenne.di

import com.duchastel.simon.solenne.data.features.Features
import com.duchastel.simon.solenne.data.features.NativeFeatures
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Binds
import dev.zacsweers.metro.SingleIn

interface NativeFeatureProviders {
    @Binds
    @SingleIn(AppScope::class)
    fun NativeFeatures.bind(): Features
}
package com.duchastel.simon.solenne.data.features

import dev.zacsweers.metro.Inject

@Inject
class NativeFeatures : Features {
    override val localMcpServerAvailable: Boolean = false
}
package com.duchastel.simon.solenne.di

import com.duchastel.simon.solenne.Database
import com.duchastel.simon.solenne.dao.DriverFactory
import dev.zacsweers.metro.Provides

interface DatabaseProviders {
    @Provides
    fun provideDatabase(driverFactory: DriverFactory): Database =
        Database(driverFactory.createDriver())
}
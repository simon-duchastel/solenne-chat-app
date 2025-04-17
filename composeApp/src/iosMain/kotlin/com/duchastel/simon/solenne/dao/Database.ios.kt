package com.duchastel.simon.solenne.dao

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.duchastel.simon.solenne.Database
import dev.zacsweers.metro.Inject

@Inject
actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(
            schema = Database.Schema.synchronous(),
            name = DB_NAME,
        )
    }

    companion object {
        private const val DB_NAME = "main.db"
    }
}
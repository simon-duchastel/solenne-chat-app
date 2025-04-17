package com.duchastel.simon.solenne.dao

import app.cash.sqldelight.db.SqlDriver
import com.duchastel.simon.solenne.Database
import app.cash.sqldelight.driver .jdbc.sqlite.JdbcSqliteDriver
import dev.zacsweers.metro.Inject

@Inject
actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        return JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY).apply {
            Database.Schema.create(this)
        }
    }
}
package com.duchastel.simon.solenne.dao

import android.content.Context
import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.duchastel.simon.solenne.Database
import dev.zacsweers.metro.Inject

actual class DriverFactory @Inject constructor(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            schema = Database.Schema.synchronous(),
            context = context,
            name = DB_NAME,
        )
    }

    companion object {
        private const val DB_NAME = "main.db"
    }
}
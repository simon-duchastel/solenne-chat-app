package com.duchastel.simon.solenne.dao

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.worker.WebWorkerDriver
import app.cash.sqldelight.driver.worker.createDefaultWebWorkerDriver
import dev.zacsweers.metro.Inject
import org.w3c.dom.Worker

@Inject
actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        return createDefaultWebWorkerDriver()
    }
}

private fun jsWorker(): Worker =
    js("""new URL("@cashapp/sqldelight-sqljs-worker/sqljs.worker.js", import.meta.url)""")
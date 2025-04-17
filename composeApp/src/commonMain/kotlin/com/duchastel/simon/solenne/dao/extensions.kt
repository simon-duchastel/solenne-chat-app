package com.duchastel.simon.solenne.dao

import app.cash.sqldelight.Query
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.jvm.JvmName

/**
 * Copied from app.cash.sqldelight.coroutines.asFlow until wasm support is released.
 * See https://github.com/sqldelight/sqldelight/pull/5534
 */
@JvmName("toFlow")
fun <T : Any> Query<T>.asFlow(): Flow<Query<T>> = flow {
    val channel = Channel<Unit>(CONFLATED)
    channel.trySend(Unit)

    val listener = Query.Listener {
        channel.trySend(Unit)
    }

    addListener(listener)
    try {
        for (item in channel) {
            emit(this@asFlow)
        }
    } finally {
        removeListener(listener)
    }
}
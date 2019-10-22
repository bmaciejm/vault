package com.bartoszmaciej.vault.common.marshmallow

import android.os.Build

class MarshmallowHelper {

    fun <T> doWithMinMarshmallow(
        minMarshmallowOperation: () -> T,
        belowMarshmallowOperation: () -> T
    ): T {
        return if (hasMarshmallow()) {
            minMarshmallowOperation.invoke()
        } else {
            belowMarshmallowOperation.invoke()
        }
    }

    private fun hasMarshmallow() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
}
package com.bartoszmaciej.vault.common.lock

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.KeyguardManager
import android.content.Context
import com.bartoszmaciej.vault.BuildConfig
import com.bartoszmaciej.vault.common.marshmallow.MarshmallowHelper

class LockScreenGuard(
    private val context: Context,
    private val marshmallowHelper: MarshmallowHelper
) {

    private val keyGuardManager by lazy { context.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager }

    fun doCheck() {
        if (!isDeviceSecure()) {
            showDeviceSecurityAlert()
        }
    }

    // TODO try to play with contracts api to remove suppress or move to methods with targetApi
    @SuppressLint("NewApi")
    private fun isDeviceSecure() = marshmallowHelper.doWithMinMarshmallow(
        { keyGuardManager.isDeviceSecure },
        { keyGuardManager.isKeyguardSecure }
    )

    // TODO add text and handling for buttons in dialog
    private fun showDeviceSecurityAlert() = AlertDialog.Builder(context)
        .setTitle("Test")
        .setMessage("test")
        .setCancelable(BuildConfig.DEBUG)
        .show()
}
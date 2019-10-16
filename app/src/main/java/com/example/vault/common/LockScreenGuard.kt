package com.example.vault.common

import android.app.AlertDialog
import android.app.KeyguardManager
import android.content.Context
import com.example.testgeoloc.BuildConfig
import com.example.vault.common.MarshmallowHelper

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

    private fun isDeviceSecure() =
        if (marshmallowHelper.hasMarshmallow()) keyGuardManager.isDeviceSecure else keyGuardManager.isKeyguardSecure

    private fun showDeviceSecurityAlert() = AlertDialog.Builder(context)
        .setTitle("Test")
        .setMessage("test")
        .setCancelable(BuildConfig.DEBUG)
        .show()
}
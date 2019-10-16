package com.example.testgeoloc.common

import android.app.AlertDialog
import android.app.KeyguardManager
import android.content.Context
import android.os.Build
import com.example.testgeoloc.BuildConfig

class LockScreenGuard(private val context: Context) {

    private val keyGuardManager by lazy { context.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager }

    fun isDeviceSecure() =
        if (hasMarshmallow()) keyGuardManager.isDeviceSecure else keyGuardManager.isKeyguardSecure

    fun showDeviceSecurityAlert() = AlertDialog.Builder(context)
        .setTitle("Test")
        .setMessage("test")
        .setCancelable(BuildConfig.DEBUG)
        .show()



    companion object{
        fun hasMarshmallow() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
    }
}
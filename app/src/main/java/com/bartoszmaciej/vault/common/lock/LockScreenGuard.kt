package com.bartoszmaciej.vault.common.lock

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.KeyguardManager
import android.content.Context
import com.bartoszmaciej.vault.BuildConfig
import com.bartoszmaciej.vault.R
import com.bartoszmaciej.vault.common.ext.openLockScreenSettings
import com.bartoszmaciej.vault.common.marshmallow.MarshmallowHelper
import kotlin.system.exitProcess

class LockScreenGuard(
  private val context: Context,
  private val marshmallowHelper: MarshmallowHelper
) {

  private val keyGuardManager by lazy { context.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager }

  fun check() {
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

  private fun showDeviceSecurityAlert() = AlertDialog.Builder(context)
    .setTitle(context.getString(R.string.lock_screen_dialog_title))
    .setMessage(context.getString(R.string.lock_screen_dialog_content))
    .setPositiveButton(R.string.lock_screen_dialog_positive_button) { _, _ -> context.openLockScreenSettings() }
    .setNegativeButton(R.string.lock_screen_dialog_negative_button) { _, _ -> exitProcess(0) }
    .setCancelable(BuildConfig.DEBUG)
    .show()
}
package com.bartoszmaciej.vault.common.lock

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.KeyguardManager
import android.content.Context
import androidx.core.content.getSystemService
import com.bartoszmaciej.vault.BuildConfig
import com.bartoszmaciej.vault.R
import com.bartoszmaciej.vault.common.ext.openLockScreenSettings
import com.bartoszmaciej.vault.common.marshmallow.MarshmallowHelper
import kotlin.system.exitProcess

class LockScreenGuard(
  private val context: Context,
  private val marshmallowHelper: MarshmallowHelper
) {

  private val keyGuardManager by lazy { context.getSystemService<KeyguardManager>()!! }

  fun check() {
    if (!isDeviceSecure()) {
      showDeviceSecurityAlert()
    }
  }

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
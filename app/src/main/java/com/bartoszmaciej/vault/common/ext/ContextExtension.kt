package com.bartoszmaciej.vault.common.ext

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.provider.Settings

fun Context.openLockScreenSettings() {
  val intent = Intent(Settings.ACTION_SECURITY_SETTINGS)
  startActivitySafely(intent)
}

fun Context.startActivitySafely(
  intent: Intent,
  errorHandler: (ActivityNotFoundException) -> Unit = {}
) {
  try {
    startActivity(intent)
  } catch (e: ActivityNotFoundException) {
    errorHandler.invoke(e)
  }
}
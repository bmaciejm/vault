package com.bartoszmaciej.vault

import android.app.Application
import com.bartoszmaciej.vault.di.asymmetricModule
import com.bartoszmaciej.vault.di.keystoreModule
import com.bartoszmaciej.vault.di.utilModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

class VaultApplication : Application() {

  override fun onCreate() {
    super.onCreate()

    startKoin {
      androidLogger()
      androidContext(this@VaultApplication)
      loadAppModules()
    }
  }

  private fun KoinApplication.loadAppModules() {
    modules(
      listOf(
        utilModule,
        keystoreModule,
        asymmetricModule
      )
    )
  }
}
package com.bartoszmaciej.vault.keystore.owner

import com.bartoszmaciej.Providers
import java.security.KeyStore

class AndroidKeystoreOwner : KeystoreOwner {

  override val keyStore: KeyStore by lazy { provideKeystore() }

  override fun removeKeyEntry(alias: String) = keyStore.deleteEntry(alias)

  private fun provideKeystore(): KeyStore = KeyStore.getInstance(Providers.ANDROID_KEYSTORE_PROVIDER).apply {
    load(null)
  }
}
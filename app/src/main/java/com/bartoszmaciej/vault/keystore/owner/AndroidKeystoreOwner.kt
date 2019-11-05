package com.bartoszmaciej.vault.keystore.owner

import com.bartoszmaciej.KEYSTORE_PROVIDER_ANDROID
import java.security.KeyStore

class AndroidKeystoreOwner : KeystoreOwner {

  override val keyStore: KeyStore by lazy { provideKeystore() }

  override fun removeKey(alias: String) = keyStore.deleteEntry(alias)

  private fun provideKeystore(): KeyStore =
    KeyStore.getInstance(KEYSTORE_PROVIDER_ANDROID).apply {
      load(null)
    }
}
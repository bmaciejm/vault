package com.bartoszmaciej.vault.crypt

import java.lang.IllegalStateException
import java.security.KeyPair
import java.security.KeyStore
import java.security.PrivateKey
import javax.crypto.SecretKey

class KeystoreHelper {

  private val keyStore = KeyStore.getInstance("AndroidKeyStore")
    .apply { load(null) }

  fun getAndroidKeyStoreSymmetricKey(alias: String) = keyStore.getKey(alias, null) as SecretKey?

  fun getKeyStoreAsymmetricKeyPair(alias: String): KeyPair {
    val privateKey = keyStore.getKey(alias, null) as PrivateKey?
    val publicKey = keyStore.getCertificate(alias)?.publicKey

    if (privateKey != null && publicKey != null) {
      return KeyPair(publicKey, privateKey)
    } else {
      throw IllegalStateException("Unable to retrieve KeyPair for given alias")
    }
  }

  fun deleteKeyStoreKey(alias: String) = keyStore.deleteEntry(alias)
}


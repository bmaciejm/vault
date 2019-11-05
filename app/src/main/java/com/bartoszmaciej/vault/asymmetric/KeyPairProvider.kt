package com.bartoszmaciej.vault.asymmetric

import com.bartoszmaciej.vault.keystore.owner.KeystoreOwner
import java.security.KeyPair
import java.security.PrivateKey

class KeyPairProvider(private val keystoreOwner: KeystoreOwner) {

  fun getAsymmetricKeyPair(alias: String): KeyPair? {
    val keystore = keystoreOwner.keyStore
    val privateKey = keystore.getKey(alias, null) as PrivateKey?
    val publicKey = keystore.getCertificate(alias)?.publicKey

    return if (privateKey != null && publicKey != null) {
      KeyPair(publicKey, privateKey)
    } else {
      null
    }
  }
}
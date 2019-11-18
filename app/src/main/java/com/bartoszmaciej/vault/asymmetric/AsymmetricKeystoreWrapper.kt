package com.bartoszmaciej.vault.asymmetric

import com.bartoszmaciej.vault.cipher.CipherOwner
import com.bartoszmaciej.vault.keystore.KeyGenerator
import com.bartoszmaciej.vault.keystore.owner.KeystoreOwner
import java.security.KeyPair

class AsymmetricKeystoreWrapper(
  keystoreOwner: KeystoreOwner,
  private val cipherOwner: CipherOwner,
  private val keyGenerator: KeyGenerator<KeyPair>,
  private val keyPairProvider: KeyPairProvider
) {

  private val keystore = keystoreOwner.keyStore

  private var keyPair: KeyPair? = null
    get() {
      if (field == null) error("Wrapper not initialized with keyPair")

      return field
    }

  fun hasAlias(alias: String): Boolean = keystore.containsAlias(alias)

  fun createAsymmetricKey(alias: String) {
    keyGenerator.generate(alias)
  }

  fun initWithKeyPair(alias: String) {
    keyPair = keyPairProvider.getAsymmetricKeyPair(alias)
  }

  fun encrypt(data: String): String = cipherOwner.encrypt(data, keyPair?.public)

  fun decrypt(data: String): String = cipherOwner.decrypt(data, keyPair?.private)

}
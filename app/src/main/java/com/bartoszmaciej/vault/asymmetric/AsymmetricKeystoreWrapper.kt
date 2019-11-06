package com.bartoszmaciej.vault.asymmetric

import com.bartoszmaciej.vault.cipher.CipherOwner
import com.bartoszmaciej.vault.keystore.KeyGenerator
import com.bartoszmaciej.vault.keystore.owner.KeystoreOwner
import java.security.KeyPair

class AsymmetricKeystoreWrapper(private val keystoreOwner: KeystoreOwner,
                                private val cipherOwner: CipherOwner,
                                private val keyGenerator: KeyGenerator<KeyPair>,
                                private val keyPairProvider: KeyPairProvider
) {
}
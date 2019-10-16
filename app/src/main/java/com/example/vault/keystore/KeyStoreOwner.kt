package com.example.vault.keystore

import java.security.KeyStore

class KeyStoreOwner {

    val keystore: KeyStore by lazy { getAndroidKeystore() }

    private fun getAndroidKeystore() = KeyStore.getInstance("AndroidKeyStore").apply {
        load(null)
    }
}
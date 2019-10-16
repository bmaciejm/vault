package com.example.vault.keystore

import java.security.KeyStore

class KeyStoreHelper {

    fun getAndroidKeystore() = KeyStore.getInstance("AndroidKeyStore").apply {
        load(null)
    }
}
package com.bartoszmaciej.vault.keystore.owner

import java.security.KeyStore

interface KeystoreOwner {

  val keyStore: KeyStore

  fun removeKey(alias: String)
}
package com.bartoszmaciej.vault.cipher

import java.security.Key
import javax.crypto.Cipher

interface CipherOwner {

  val cipher: Cipher

  fun encrypt(data: String, key: Key?): String

  fun decrypt(data: String, key: Key?): String
}
package com.bartoszmaciej.vault.crypt

import javax.crypto.KeyGenerator

object KeysGenerator {

  fun generateDefaultSymmetricKey() = KeyGenerator
    .getInstance("AES", "BC")
    .apply { generateKey() }


}
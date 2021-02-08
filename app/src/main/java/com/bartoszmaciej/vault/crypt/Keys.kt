package com.bartoszmaciej.vault.crypt

import android.util.Base64
import com.bartoszmaciej.Transformations
import java.security.Key
import javax.crypto.Cipher

object Keys {

  fun wrapKey(keyToBeWrapped: Key, keyToWrapWith: Key?): String {
    val cipher = Cipher.getInstance(Transformations.TRANSFORMATION_ASYMMETRIC)
    cipher.init(Cipher.WRAP_MODE, keyToWrapWith)
    val decodedData = cipher.wrap(keyToBeWrapped)
    return Base64.encodeToString(decodedData, Base64.DEFAULT)
  }

  fun unwrapKey(wrappedKey: String, algorithm: String, wrappedKeyType: Int, keyToUnWrapWith: Key?): Key {
    val encryptedKeyData = Base64.decode(wrappedKey, Base64.DEFAULT)
    val cipher = Cipher.getInstance(Transformations.TRANSFORMATION_ASYMMETRIC)
    cipher.init(Cipher.UNWRAP_MODE, keyToUnWrapWith)
    return cipher.unwrap(encryptedKeyData, algorithm, wrappedKeyType)
  }
}
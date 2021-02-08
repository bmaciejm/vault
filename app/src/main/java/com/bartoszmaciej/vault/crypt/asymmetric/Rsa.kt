package com.bartoszmaciej.vault.crypt.asymmetric

import android.util.Base64
import com.bartoszmaciej.vault.crypt.DecryptionStrategy
import com.bartoszmaciej.vault.crypt.EncryptionStrategy
import com.bartoszmaciej.Transformations.TRANSFORMATION_ASYMMETRIC
import java.security.Key
import javax.crypto.Cipher

class Rsa(private val data: String, private val key: Key?) : EncryptionStrategy<String>, DecryptionStrategy<String> {

  private val cipher by lazy { Cipher.getInstance(TRANSFORMATION_ASYMMETRIC) }

  override fun encrypt(): String {
    cipher.init(Cipher.ENCRYPT_MODE, key)
    val bytes = cipher.doFinal(data.toByteArray())
    return Base64.encodeToString(bytes, Base64.DEFAULT)
  }

  override fun decrypt(): String {
    cipher.init(Cipher.DECRYPT_MODE, key)
    val encryptedData = Base64.decode(data, Base64.DEFAULT)
    val decodedData = cipher.doFinal(encryptedData)
    return String(decodedData)
  }
}
package com.bartoszmaciej.vault.asymmetric

import android.util.Base64
import com.bartoszmaciej.Transformations
import com.bartoszmaciej.vault.cipher.CipherOwner
import java.security.Key
import javax.crypto.Cipher

class AsymmetricCipherOwner : CipherOwner {

  override val cipher: Cipher by lazy { Cipher.getInstance(Transformations.TRANSFORMATION_ASYMMETRIC) }

  override fun encrypt(data: String, key: Key?): String {
    cipher.init(Cipher.ENCRYPT_MODE, key)
    val bytes = cipher.doFinal(data.toByteArray())
    return Base64.encodeToString(bytes, Base64.DEFAULT)
  }

  override fun decrypt(data: String, key: Key?): String {
    cipher.init(Cipher.DECRYPT_MODE, key)
    val encryptedData = Base64.decode(data, Base64.DEFAULT)
    val decodedData = cipher.doFinal(encryptedData)
    return String(decodedData)
  }
}
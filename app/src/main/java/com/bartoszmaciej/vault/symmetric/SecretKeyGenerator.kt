package com.bartoszmaciej.vault.symmetric

import android.annotation.TargetApi
import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import com.bartoszmaciej.Algorithms
import com.bartoszmaciej.Providers
import com.bartoszmaciej.vault.common.marshmallow.MarshmallowHelper
import com.bartoszmaciej.vault.keystore.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.KeyGenerator as keyGenerator

class SecretKeyGenerator(private val marshmallowHelper: MarshmallowHelper) : KeyGenerator<SecretKey> {

  override fun generate(alias: String): SecretKey {
    return marshmallowHelper.doWithMinMarshmallow(
      { generateAndroidSecretKey(alias) },
      { generateDefaultSecretKey() })
  }

  @TargetApi(Build.VERSION_CODES.M)
  private fun generateAndroidSecretKey(alias: String): SecretKey {
    val keyGenerator = keyGenerator.getInstance(Algorithms.KEY_ALGORITHM_AES, Providers.ANDROID_KEYSTORE_PROVIDER)
    val builder = KeyGenParameterSpec.Builder(alias, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
      .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
      .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
    keyGenerator.init(builder.build())
    return keyGenerator.generateKey()
  }

  private fun generateDefaultSecretKey(): SecretKey {
    val keyGenerator = keyGenerator.getInstance(Algorithms.KEY_ALGORITHM_AES)
    return keyGenerator.generateKey()
  }
}
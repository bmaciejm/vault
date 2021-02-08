package com.bartoszmaciej.vault.crypt

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.security.KeyPairGeneratorSpec
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import com.bartoszmaciej.vault.common.marshmallow.MarshmallowHelper
import java.math.BigInteger
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.SecureRandom
import java.util.Calendar
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec
import javax.security.auth.x500.X500Principal

class KeysGenerator(
  private val context: Context,
  private val marshmallowHelper: MarshmallowHelper
) {

  fun generateDefaultSymmetricKey() = KeyGenerator
    .getInstance("AES", "BC")
    .apply { generateKey() }

  @TargetApi(Build.VERSION_CODES.M)
  fun createAndroidSymmetricKey(alias: String): SecretKey {
    val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore")
    val builder = KeyGenParameterSpec.Builder(alias, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
      .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
      .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
    keyGenerator.init(builder.build())
    return keyGenerator.generateKey()
  }

  fun generateAsymmetricKey(alias: String): KeyPair {
    val generator = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore")

    marshmallowHelper.doWithMinMarshmallow(
      { initGeneratorKeyGenParameterSpec(generator, alias) },
      { initGeneratorKeyPairGeneratorSpec(generator, alias) }
    )

    //    floor(n/8)-11 bytes   Determine RSA max message length

    return generator.genKeyPair()
  }

  fun deriveKey(password: String, keyLength: Int = 256) {
    val iterationCount = 1000
    val keyLenght = keyLength
    val saltLength = keyLength / 8

    val random = SecureRandom()
    val salt = ByteArray(saltLength)
    random.nextBytes(salt)
    val keySpec = PBEKeySpec(password.toCharArray(), salt, iterationCount, keyLength)
    val keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
    val keyBytes = keyFactory.generateSecret(keySpec).encoded

    val key = SecretKeySpec(keyBytes, "AES")

    val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    val iv = ByteArray(cipher.blockSize)
    random.nextBytes(iv);
    val  ivParams =  IvParameterSpec(iv);
  }

  private fun initGeneratorKeyPairGeneratorSpec(generator: KeyPairGenerator, alias: String) {
    val startDate = Calendar.getInstance()
    val endDate = Calendar.getInstance()
    endDate.add(Calendar.YEAR, 10)

    val builder = KeyPairGeneratorSpec.Builder(context)
      .setAlias(alias)
      .setSerialNumber(BigInteger.ONE)
      .setSubject(X500Principal("CN=${alias} CA Certificate"))
      .setStartDate(startDate.time)
      .setEndDate(endDate.time)

    generator.initialize(builder.build())
  }

  @TargetApi(Build.VERSION_CODES.M)
  private fun initGeneratorKeyGenParameterSpec(generator: KeyPairGenerator, alias: String) {
    val builder = KeyGenParameterSpec.Builder(alias, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
      .setBlockModes(KeyProperties.BLOCK_MODE_ECB)
      .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)
    //      .setCertificateNotBefore(startDate) // By default, this date is Jan 1 1970.
    //      .setCertificateNotAfter(endDate) // By default, this date is Jan 1 2048.
    //      .setCertificateSerialNumber(number) // By default, the serial number is 1.
    //      .setCertificateSubject(x500Principal) // By default, the subject is CN=fake.

    generator.initialize(builder.build())
  }

}
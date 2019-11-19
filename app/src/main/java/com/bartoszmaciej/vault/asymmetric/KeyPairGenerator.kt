package com.bartoszmaciej.vault.asymmetric

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.security.KeyPairGeneratorSpec
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import com.bartoszmaciej.Algorithms
import com.bartoszmaciej.Providers
import com.bartoszmaciej.vault.common.marshmallow.MarshmallowHelper
import com.bartoszmaciej.vault.keystore.KeyGenerator
import java.math.BigInteger
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.util.Calendar
import javax.security.auth.x500.X500Principal

class KeyPairGenerator(
  private val context: Context,
  private val marshmallowHelper: MarshmallowHelper
) : KeyGenerator<KeyPair> {

  override fun generate(alias: String): KeyPair {
    val generator = KeyPairGenerator.getInstance(Algorithms.KEY_ALGORITHM_RSA, Providers.ANDROID_KEYSTORE_PROVIDER)

    marshmallowHelper.doWithMinMarshmallow(
      { initGeneratorWithKeyGen(generator, alias) },
      { initGeneratorWithKeyPair(generator, alias) }
    )

    return generator.generateKeyPair()
  }

  @TargetApi(Build.VERSION_CODES.M)
  private fun initGeneratorWithKeyGen(generator: KeyPairGenerator, alias: String) {
    val (startDate, endDate) = provideValidityDates()

    val builder = KeyGenParameterSpec.Builder(
      alias,
      KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
    )
      .setBlockModes(KeyProperties.BLOCK_MODE_ECB)
      .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)
      .setCertificateNotBefore(startDate)
      .setCertificateNotAfter(endDate)

    generator.initialize(builder.build())
  }

  private fun initGeneratorWithKeyPair(generator: KeyPairGenerator, alias: String) {
    val (startDate, endDate) = provideValidityDates()

    val builder = KeyPairGeneratorSpec.Builder(context)
      .setAlias(alias)
      .setSerialNumber(BigInteger.ONE)
      .setSubject(X500Principal("CN=$alias CA Certificate"))
      .setStartDate(startDate)
      .setEndDate(endDate)

    generator.initialize(builder.build())
  }

  private fun provideValidityDates() = Calendar.getInstance().time to Calendar.getInstance().apply { add(Calendar.YEAR, 10) }.time
}
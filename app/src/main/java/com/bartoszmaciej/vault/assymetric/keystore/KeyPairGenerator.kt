package com.bartoszmaciej.vault.assymetric.keystore

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.security.KeyPairGeneratorSpec
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import com.bartoszmaciej.KEYSTORE_PROVIDER_ANDROID
import com.bartoszmaciej.KEY_ALGORITHM_RSA
import com.bartoszmaciej.vault.common.marshmallow.MarshmallowHelper
import com.bartoszmaciej.vault.keystore.KeyGenerator
import java.math.BigInteger
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.util.*
import javax.security.auth.x500.X500Principal

class KeyPairGenerator(
    private val context: Context,
    private val marshmallowHelper: MarshmallowHelper
) : KeyGenerator<KeyPair> {


    override fun generate(alias: String): KeyPair {
        val generator = KeyPairGenerator.getInstance(KEY_ALGORITHM_RSA, KEYSTORE_PROVIDER_ANDROID)

        marshmallowHelper.doWithMinMarshmallow(
            { initGeneratorWithKeyGen(generator, alias) },
            { initGeneratorWithKeyPair(generator, alias) }
        )

        return generator.generateKeyPair()
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun initGeneratorWithKeyGen(generator: KeyPairGenerator, alias: String) {
        val builder = KeyGenParameterSpec.Builder(
            alias,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_ECB)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)

        generator.initialize(builder.build())
    }

    // TODO read about security concerns with "old" keystore
    private fun initGeneratorWithKeyPair(generator: KeyPairGenerator, alias: String) {
        val startDate = Calendar.getInstance()
        val endDate = Calendar.getInstance()
        endDate.add(Calendar.YEAR, 10)

        val builder = KeyPairGeneratorSpec.Builder(context)
            .setAlias(alias)
            .setSerialNumber(BigInteger.ONE)
            .setSubject(X500Principal("CN=$alias CA Certificate"))
            .setStartDate(startDate.time)
            .setEndDate(endDate.time)

        generator.initialize(builder.build())
    }
}
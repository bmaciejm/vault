package com.example.vault.keystore

import com.example.vault.common.MarshmallowHelper
import java.security.KeyPair
import java.security.KeyPairGenerator

class KeyGenerator(private val marshmallowHelper: MarshmallowHelper) {


    fun createAndroidKeyStoreAsymmetricKey(): KeyPair{
        val generator = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore")

        if(marshmallowHelper.hasMarshmallow()){

        }

        return generator.generateKeyPair()
    }




}
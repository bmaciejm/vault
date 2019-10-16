package com.example.testgeoloc.keystore

import com.example.testgeoloc.common.LockScreenGuard
import java.security.KeyPair
import java.security.KeyPairGenerator

class KeyGenerator {


    fun createAndroidKeyStoreAsymmetricKey(): KeyPair{
        val generator = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore")

        if(LockScreenGuard.hasMarshmallow()){

        }

        return generator.generateKeyPair()
    }




}
package com.bartoszmaciej.vault.di

import com.bartoszmaciej.vault.assymetric.KeyPairProvider
import com.bartoszmaciej.vault.assymetric.keystore.KeyPairGenerator
import com.bartoszmaciej.vault.keystore.owner.AndroidKeystoreOwner
import com.bartoszmaciej.vault.common.lock.LockScreenGuard
import com.bartoszmaciej.vault.common.marshmallow.MarshmallowHelper
import org.koin.dsl.module

val utilModule = module {
    factory { MarshmallowHelper() }

    single { LockScreenGuard(get(), get()) }
}


val keystoreModule = module {
    single { AndroidKeystoreOwner() }
}

val assymetricModule = module {
    single { KeyPairGenerator(get(), get()) }

    single { KeyPairProvider(get()) }
}
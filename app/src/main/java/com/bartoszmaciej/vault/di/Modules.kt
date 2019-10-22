package com.bartoszmaciej.vault.di

import com.bartoszmaciej.vault.keystore.KeyGenerator
import com.bartoszmaciej.vault.keystore.KeyStoreOwner
import com.bartoszmaciej.vault.common.lock.LockScreenGuard
import com.bartoszmaciej.vault.common.marshmallow.MarshmallowHelper
import org.koin.dsl.module

val utilModule = module {
    factory { MarshmallowHelper() }

    single { LockScreenGuard(get(), get()) }
}


val keystoreModule = module {
    single { KeyStoreOwner() }

    single { KeyGenerator(get(), get()) }
}
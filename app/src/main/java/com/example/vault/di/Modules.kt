package com.example.vault.di

import com.example.vault.keystore.KeyGenerator
import com.example.vault.keystore.KeyStoreOwner
import com.example.vault.common.lock.LockScreenGuard
import com.example.vault.common.marshmallow.MarshmallowHelper
import org.koin.dsl.module

val utilModule = module {
    factory { MarshmallowHelper() }

    single { LockScreenGuard(get(), get()) }
}


val keystoreModule = module {
    single { KeyStoreOwner() }

    single { KeyGenerator(get(), get()) }
}
package com.example.vault.di

import com.example.vault.keystore.KeyGenerator
import com.example.vault.keystore.KeyStoreHelper
import com.example.vault.common.LockScreenGuard
import com.example.vault.common.MarshmallowHelper
import org.koin.dsl.module

val utilModule = module {
    factory { MarshmallowHelper() }

    single { LockScreenGuard(get(), get()) }
}


val keystoreModule = module {
    factory { KeyStoreHelper() }

    single { KeyGenerator(get()) }
}
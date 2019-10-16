package com.example.testgeoloc.di

import com.example.testgeoloc.keystore.KeyGenerator
import com.example.testgeoloc.keystore.KeyStoreHelper
import com.example.testgeoloc.common.LockScreenGuard
import com.example.testgeoloc.common.MarshmallowHelper
import org.koin.dsl.module

val utilModule = module {
    factory { MarshmallowHelper() }

    single { LockScreenGuard(get()) }
}


val keystoreModule = module{
    single { KeyStoreHelper() }

    single { KeyGenerator() }
}
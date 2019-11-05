package com.bartoszmaciej.vault.di

import android.content.Context
import com.bartoszmaciej.vault.asymmetric.KeyPairProvider
import com.bartoszmaciej.vault.asymmetric.KeyPairGenerator
import com.bartoszmaciej.vault.keystore.owner.AndroidKeystoreOwner
import com.bartoszmaciej.vault.common.lock.LockScreenGuard
import com.bartoszmaciej.vault.common.marshmallow.MarshmallowHelper
import org.koin.dsl.module

val utilModule = module {
  factory { MarshmallowHelper() }

  single { (context: Context) -> LockScreenGuard(context, get()) }
}

val keystoreModule = module {
  single { AndroidKeystoreOwner() }
}

val asymmetricModule = module {
  single { KeyPairGenerator(get(), get()) }

  single { KeyPairProvider(get()) }
}
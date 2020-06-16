package com.bartoszmaciej.vault.di

import android.content.Context
import com.bartoszmaciej.vault.common.lock.LockScreenGuard
import com.bartoszmaciej.vault.common.marshmallow.MarshmallowHelper
import org.koin.dsl.module

val utilModule = module {
  factory { MarshmallowHelper() }

  single { (context: Context) -> LockScreenGuard(context, get()) }
}
package com.bartoszmaciej.vault.di

import android.content.Context
import com.bartoszmaciej.vault.asymmetric.AsymmetricCipherOwner
import com.bartoszmaciej.vault.asymmetric.AsymmetricKeystoreWrapper
import com.bartoszmaciej.vault.asymmetric.KeyPairGenerator
import com.bartoszmaciej.vault.asymmetric.KeyPairProvider
import com.bartoszmaciej.vault.cipher.CipherOwner
import com.bartoszmaciej.vault.common.lock.LockScreenGuard
import com.bartoszmaciej.vault.common.marshmallow.MarshmallowHelper
import com.bartoszmaciej.vault.keystore.KeyGenerator
import com.bartoszmaciej.vault.keystore.owner.AndroidKeystoreOwner
import com.bartoszmaciej.vault.keystore.owner.KeystoreOwner
import com.bartoszmaciej.vault.symmetric.DefaultSecretKeyGenerator
import com.bartoszmaciej.vault.symmetric.SecretKeyGenerator
import org.koin.core.qualifier.named
import org.koin.dsl.module
import java.security.KeyPair
import javax.crypto.SecretKey

val utilModule = module {
  factory { MarshmallowHelper() }

  single { (context: Context) -> LockScreenGuard(context, get()) }
}

val keystoreModule = module {
  single<KeystoreOwner>(named("android")) { AndroidKeystoreOwner() }
}

val asymmetricModule = module {
  single<KeyGenerator<KeyPair>> { KeyPairGenerator(get(), get()) }

  single { KeyPairProvider(get(qualifier = named("android"))) }

  single<CipherOwner>(named("asymmetric")) { AsymmetricCipherOwner() }

  factory {
    AsymmetricKeystoreWrapper(get(qualifier = named("android")),
                              get(qualifier = named("asymmetric")),
                              get(),
                              get())
  }
}

val symmetricModule = module {
  single<KeyGenerator<SecretKey>> { SecretKeyGenerator(get())  }
}
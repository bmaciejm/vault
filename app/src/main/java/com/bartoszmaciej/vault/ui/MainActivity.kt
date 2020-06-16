package com.bartoszmaciej.vault.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bartoszmaciej.vault.R
import com.bartoszmaciej.vault.common.lock.LockScreenGuard
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.security.Security

class MainActivity : AppCompatActivity() {

  private val TEST = "TES"

  private val lockScreenGuard: LockScreenGuard by inject { parametersOf(this) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    button.setOnClickListener { test() }
  }

  override fun onResume() {
    super.onResume()
    //    lockScreenGuard.check()
  }

  fun test() {
    Security.getProviders().forEach { Log.d("Prov", it.name) }

    //    if (!asymmetricWrapper.hasAlias(TEST)) {
    //      asymmetricWrapper.createAsymmetricKey(TEST)
    //    }
    //
    //    asymmetricWrapper.initWithKeyPair(TEST)
    //
    //      val encrypted = asymmetricWrapper.encrypt("brrrrr")
    //
    //      Log.d("TST", encrypted)
    //
    //
    //    val decrypted = asymmetricWrapper.decrypt(encrypted)
    //
    //    Log.d("TST", decrypted)

  }
}

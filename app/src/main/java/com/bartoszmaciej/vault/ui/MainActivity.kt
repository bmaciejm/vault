package com.bartoszmaciej.vault.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bartoszmaciej.vault.R
import com.bartoszmaciej.vault.asymmetric.AsymmetricKeystoreWrapper
import com.bartoszmaciej.vault.common.lock.LockScreenGuard
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity() {

  private val lockScreenGuard: LockScreenGuard by inject { parametersOf(this) }

  private val asymmetricKeystoreWrapper: AsymmetricKeystoreWrapper by inject()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    button.setOnClickListener { }
  }

  override fun onResume() {
    super.onResume()
    lockScreenGuard.check()
  }
}

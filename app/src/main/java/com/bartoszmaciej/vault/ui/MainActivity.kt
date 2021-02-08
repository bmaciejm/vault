package com.bartoszmaciej.vault.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.bartoszmaciej.vault.R
import com.bartoszmaciej.vault.common.lock.LockScreenGuard
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.security.Security

class MainActivity : AppCompatActivity() {

  private val TEST = "TES"

  private val lockScreenGuard: LockScreenGuard by inject { parametersOf(this) }

  private lateinit var biometricPrompt: BiometricPrompt

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    button.setOnClickListener { test() }
  }

  override fun onResume() {
    super.onResume()
    //    lockScreenGuard.check()
  }

  private fun createBiometricPrompt(): BiometricPrompt {
    val callback = object : BiometricPrompt.AuthenticationCallback() {
      override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
        super.onAuthenticationError(errorCode, errString)
        Log.d("bbb", "$errorCode :: $errString")
      }

      override fun onAuthenticationFailed() {
        super.onAuthenticationFailed()
        Log.d("bbb", "Authentication failed for an unknown reason")
      }

      override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
        super.onAuthenticationSucceeded(result)
        Log.d("bbb", "Authentication was successful")
        processData(result.cryptoObject)
      }
    }

    //The API requires the client/Activity context for displaying the prompt
    return BiometricPrompt(this, ContextCompat.getMainExecutor(this), callback)
  }

  fun processData(crypto: BiometricPrompt.CryptoObject?) {

  }

  fun test() {
    Security.getProviders().forEach { Log.d("Prov", it.name) }
  }
}

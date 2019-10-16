package com.example.vault.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testgeoloc.R
import com.example.vault.common.lock.LockScreenGuard
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val lockScreenGuard: LockScreenGuard by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener { }
    }

    override fun onResume() {
        super.onResume()
        lockScreenGuard.doCheck()
    }
}

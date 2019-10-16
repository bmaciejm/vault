package com.example.testgeoloc.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testgeoloc.R
import com.example.testgeoloc.common.LockScreenGuard
import com.example.testgeoloc.common.MarshmallowHelper
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val lockScreenGuard : LockScreenGuard by inject()

    private val marshmallowHelper : MarshmallowHelper by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener { }
    }

    override fun onResume() {
        super.onResume()
        lockScreenGuard.apply {
            if (!isDeviceSecure()) {
                showDeviceSecurityAlert()
            }
        }
    }
}

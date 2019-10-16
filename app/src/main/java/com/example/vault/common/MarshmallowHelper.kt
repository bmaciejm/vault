package com.example.testgeoloc.common

import android.os.Build

class MarshmallowHelper {

    fun hasMarshmallow() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
}
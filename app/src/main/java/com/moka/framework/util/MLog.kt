package com.moka.framework.util

import android.util.Log

object MLog {

    fun e(a: String, message: String) {
        if (TestUtil.isDebugMode)
            Log.e(a, message)
    }

    fun i(a: String, message: String) {
        if (TestUtil.isDebugMode)
            Log.i(a, message)
    }

    fun wtf(a: String, message: String) {
        if (TestUtil.isDebugMode)
            Log.wtf(a, message)
    }

    fun d(a: String, message: String) {
        if (TestUtil.isDebugMode)
            Log.d(a, message)
    }

    fun a(a: String, message: String) {
        if (TestUtil.isDebugMode)
            Log.println(Log.ASSERT, a, message)
    }

    fun deb(message: String) {
        if (TestUtil.isDebugMode)
            Log.i("debugging..", message)
    }
}
package com.moka.mokatoyapp

import android.app.Application
import android.content.Context

class MokaToyApplication : Application() {

    companion object {
        lateinit var context: Context
            private set
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }

}
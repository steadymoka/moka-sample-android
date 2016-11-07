package com.moka.mokatoyapp

import android.app.Application
import android.content.Context
import com.moka.mokatoyapp.model.RealmHelper

class MokaToyApplication : Application() {

    companion object {
        lateinit var context: Context
            private set
    }

    override fun onCreate() {
        super.onCreate()
        context = this

        RealmHelper.init(this)
    }

}
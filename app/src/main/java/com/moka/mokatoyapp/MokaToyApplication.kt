package com.moka.mokatoyapp

import android.app.Application
import android.content.Context
import com.moka.mokatoyapp._di.component.ApplicationComponent
import com.moka.mokatoyapp._di.component.DaggerApplicationComponent
import com.moka.mokatoyapp._di.module.ApiModule
import com.moka.mokatoyapp._di.module.ApplicationModule
import com.moka.mokatoyapp._di.module.RepositoryModule
import com.moka.mokatoyapp.model.RealmHelper

class MokaToyApplication : Application() {

    companion object {
        lateinit var context: Context
            private set
    }

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        context = this
        applicationComponent = createComponent()

        RealmHelper.init(this)
    }

    private fun createComponent(): ApplicationComponent {
        return DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .apiModule(ApiModule(this))
                .repositoryModule(RepositoryModule())
                .build()
    }

}
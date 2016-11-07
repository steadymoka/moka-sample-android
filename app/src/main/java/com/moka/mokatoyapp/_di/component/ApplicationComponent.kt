package com.moka.mokatoyapp._di.component

import android.content.Context
import com.moka.mokatoyapp._di.module.ApiModule
import com.moka.mokatoyapp._di.module.ApplicationModule
import com.moka.mokatoyapp.server.api.MokaAPI
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, ApiModule::class))
interface ApplicationComponent {

    fun context(): Context
    fun mokaAPI(): MokaAPI.API

}
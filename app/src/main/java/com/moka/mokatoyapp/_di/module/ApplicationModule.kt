package com.moka.mokatoyapp._di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(application: Application) {

    private lateinit var application: Application

    init {
        this.application = application
    }

    @Provides
    @Singleton
    fun context() = application

}
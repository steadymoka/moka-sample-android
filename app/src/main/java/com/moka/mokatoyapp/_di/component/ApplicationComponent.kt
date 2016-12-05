package com.moka.mokatoyapp._di.component

import android.content.Context
import com.moka.mokatoyapp._di.module.ApiModule
import com.moka.mokatoyapp._di.module.ApplicationModule
import com.moka.mokatoyapp._di.module.RepositoryModule
import com.moka.mokatoyapp.model.repository.ITaskRepository
import com.moka.mokatoyapp.model.repository.TaskRepository
import com.moka.mokatoyapp.server.api.MokaAPI
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, ApiModule::class, RepositoryModule::class))
interface ApplicationComponent {

    fun context(): Context
    fun mokaAPI(): MokaAPI.API
    fun taskRepository(): ITaskRepository

}
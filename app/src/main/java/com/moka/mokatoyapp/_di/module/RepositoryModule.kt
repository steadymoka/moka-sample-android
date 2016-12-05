package com.moka.mokatoyapp._di.module

import com.moka.mokatoyapp.model.repository.ITaskRepository
import com.moka.mokatoyapp.model.repository.TaskRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideTaskRepository(): ITaskRepository {
        return TaskRepository()
    }

}
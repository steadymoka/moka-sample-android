package com.moka.mokatoyapp.model.repository

import com.moka.mokatoyapp.model.domain.Task
import rx.Observable

interface ITaskRepository : BaseRepository<Task> {

    fun getTasks(filterStatus: Int = 0): Observable<Task>

}
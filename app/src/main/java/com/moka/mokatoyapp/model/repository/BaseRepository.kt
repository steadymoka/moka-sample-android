package com.moka.mokatoyapp.model.repository

import com.moka.mokatoyapp.model.domain.BaseDomain
import rx.Observable

interface BaseRepository<T : BaseDomain> {

    fun insert(work: (domain: T) -> Unit)

    fun update(domain: T, work: (domain: T) -> Unit)

    fun delete(id: Long)

    fun get(id: Long): Observable<T>

}
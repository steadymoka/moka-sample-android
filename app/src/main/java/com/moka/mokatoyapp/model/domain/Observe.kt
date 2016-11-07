package com.moka.mokatoyapp.model.domain

import rx.Observable
import rx.Subscription
import rx.subjects.PublishSubject

class Observe<T : BaseDomain> {

    private var subject: PublishSubject<Data<T>> = PublishSubject.create<Data<T>>()

    fun onInsert(model: T) {
        val data = Data(model)
        data.isCreated = true

        subject.onNext(data)
    }

    fun onUpdate(model: T) {
        val data = Data(model)
        data.isUpdated = true

        subject.onNext(data)
    }

    fun onDelete(model: T) {
        val data = Data(model)
        data.isDeleted = true

        subject.onNext(data)
    }

    fun setOnChange(callback: (model: Data<T>) -> Unit, initModel: T? = null): Subscription {
        val subscription = subject
                .onBackpressureBuffer()
                .retry(1)
                .subscribe(
                        { model -> callback(model) },
                        { e -> },
                        {})

        if (null != initModel) {
            val data = Data(initModel)
            data.isUpdated = true
            callback(data)
        }

        return subscription
    }

    fun setOnChangeObservable(callback: (observable: Observable<Data<T>>) -> Unit) {
        callback(subject
                .onBackpressureBuffer()
                .retry(1))
    }

    data class Data<out T : BaseDomain>(val data: T) {
        var isCreated: Boolean = false
        var isUpdated: Boolean = false
        var isDeleted: Boolean = false
    }

}
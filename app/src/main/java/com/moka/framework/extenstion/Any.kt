package com.moka.framework.extenstion

import android.app.Activity
import android.content.Context
import android.widget.Toast
import rx.Completable
import rx.Observable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit

fun Any.workBack(work: () -> Unit) {
    Observable.just(true)
            .observeOn(Schedulers.io())
            .subscribe({ work() })
//    Schedulers.io().createWorker().schedule { work() }
}

fun Any.workInBackground(work: () -> Unit): Completable {
    return Completable.create { work }
            .subscribeOn(Schedulers.io())

}

fun <T> Any.workInBack(work: () -> T): Observable<T> {
    return Observable.just(true)
            .observeOn(Schedulers.io())
            .map { work() }
}

fun showToast(activity: Activity, text: String) {
    Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
}

fun showToast(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}

fun postDelay(work: () -> Unit, timeMillis: Long = 500): Subscription {
    return Observable.timer(timeMillis, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ work() }, { e -> }, {})
}
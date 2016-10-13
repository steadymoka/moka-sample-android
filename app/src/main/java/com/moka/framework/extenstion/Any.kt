package com.moka.framework.extenstion

import android.app.Activity
import android.content.Context
import android.widget.Toast
import rx.Observable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit

fun Any.workInBack(work: () -> Unit) {
    Observable.just(true)
            .observeOn(Schedulers.io())
            .subscribe({ work() })
}

fun Any.workInBackground(work: () -> Unit) {
    Schedulers.io().createWorker().schedule { work() }
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
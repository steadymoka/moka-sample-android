package com.moka.framework.extenstion

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.moka.mokatoyapp.R
import rx.Observable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit

fun workInBack(work: () -> Unit) {
    Observable.just(true)
            .observeOn(Schedulers.io())
            .subscribe({ work() })
}

fun workInBackground(work: () -> Unit) {
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

fun hideSoftKey(activity: Activity) {
    val inputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val currentFocus = activity.currentFocus
    if (null != currentFocus)
        inputMethodManager.hideSoftInputFromWindow(currentFocus.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
}

/**
 * Start Activity Utils
 **/

fun Activity.startOnAnim(toGoClass: Class<*>) {
    this.startActivity(Intent(this, toGoClass))
    this.overridePendingTransition(R.anim.slide_in_right, R.anim.fade_out_short)
}

fun Activity.startOnAnim(intent: Intent) {
    this.startActivity(intent)
    this.overridePendingTransition(R.anim.slide_in_right, R.anim.fade_out_short)
}

fun Activity.startNoAnim(toGoClass: Class<*>) {
    this.startActivity(Intent(this, toGoClass))
    this.overridePendingTransition(R.anim.fade_in_short, R.anim.fade_in_short)
}

fun Activity.startNoAnim(intent: Intent) {
    this.startActivity(intent)
    this.overridePendingTransition(R.anim.fade_in_short, R.anim.fade_in_short)
}
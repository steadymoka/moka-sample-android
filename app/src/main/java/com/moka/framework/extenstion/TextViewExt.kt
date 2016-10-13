package com.moka.framework.extenstion

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.TextView
import com.moka.mokatoyapp.R
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

fun TextView.toColorRed(context: Context) {
    this.setTextColor(ContextCompat.getColor(context, R.color.red))
}

fun TextView.toColor(context: Context, resId: Int) {
    this.setTextColor(ContextCompat.getColor(context, resId))
}

fun TextView.twinkle(second: Long = 2L) {
    Observable.concat(
            Observable.just(View.VISIBLE).delay(500, TimeUnit.MILLISECONDS),
            Observable.just(View.GONE).delay(second, TimeUnit.SECONDS))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                this@twinkle.visibility = it
            }, { e -> }, {})
}
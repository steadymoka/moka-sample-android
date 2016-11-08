package com.moka.framework.extenstion

import android.app.Activity
import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.moka.framework.widget.adapter.BaseAdapter
import com.moka.framework.widget.adapter.ItemData
import com.moka.framework.widget.adapter.RecyclerItemView
import com.moka.mokatoyapp.R
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * recyclerView
 */

fun RecyclerView.hideSoftKeyOnScroll(activity: Activity) {
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            hideSoftKey(activity)
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
        }
    })
}

fun <T : ItemData, V : RecyclerItemView<T>> RecyclerView.init(activity: Activity, adapter: BaseAdapter<T, V>, spanCount: Int = 1) {
    this.layoutManager = GridLayoutManager(activity, spanCount, GridLayoutManager.VERTICAL, false)
    adapter.items = ArrayList<T>()
    this.adapter = adapter
}

fun <T : ItemData, V : RecyclerItemView<T>> RecyclerView.initReverse(activity: Activity, adapter: BaseAdapter<T, V>, spanCount: Int = 1) {
    this.layoutManager = GridLayoutManager(activity, spanCount, GridLayoutManager.VERTICAL, true)
    adapter.items = ArrayList<T>()
    this.adapter = adapter
}

/**
 * textView
 */

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

/**
 * editTextView
 */

fun EditText.countValid(cnt: Int, callback: () -> Unit) {
    this.addTextChangedListener(object : TextWatcher {

        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val text = this@countValid.text.toString()
            val textCount = text.length

            if (cnt < textCount) {
                this@countValid.setText(text.substring(0, cnt))
                this@countValid.setSelection(cnt)
                callback()
            }
        }

    })
}
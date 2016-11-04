package com.moka.framework.extenstion

import android.app.Activity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.moka.framework.widget.adapter.BaseAdapter
import com.moka.framework.widget.adapter.ItemData
import com.moka.framework.widget.adapter.RecyclerItemView
import java.util.*

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

fun <T : ItemData, V : RecyclerItemView<T>> RecyclerView.init(activity: Activity, adapter: BaseAdapter<T, V>) {
    this.layoutManager = LinearLayoutManager(activity, GridLayoutManager.VERTICAL, false)
    adapter.items = ArrayList<T>()
    this.adapter = adapter
}
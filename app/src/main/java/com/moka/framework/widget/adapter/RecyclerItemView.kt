package com.moka.framework.widget.adapter


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View


abstract class RecyclerItemView<DATA : ItemData>(val context: Context, val viewHolder: View) : RecyclerView.ViewHolder(viewHolder) {
    var index: Int = 0

    lateinit var data: DATA

    fun setItemData(data: DATA) {
        this.data = data
        refreshView(data)
    }

    var preData: DATA? = null
    var afterData: DATA? = null

    fun findViewById(resId: Int): View {
        return viewHolder.findViewById(resId)
    }

    protected abstract fun refreshView(data: DATA)

}

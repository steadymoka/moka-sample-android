package com.moka.framework.widget.adapter

import android.view.View

interface IAdapterModel {

    fun getItemList(): MutableList<ItemData>

    fun add(data: ItemData?)

    fun add(index: Int, data: ItemData?)

    fun addAll(items: List<ItemData>?)

    fun remove(item: ItemData?)

    fun addHeaderView(headerView: View)

    fun addFooterView(footerView: View)

    fun removeHeaderView()

    fun removeFooterView()

    fun clear()

    fun notifyItemChanged(data: ItemData?)

}
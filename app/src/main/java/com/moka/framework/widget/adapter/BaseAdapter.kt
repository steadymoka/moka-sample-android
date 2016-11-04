package com.moka.framework.widget.adapter


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import java.util.*


abstract class BaseAdapter<DATA : ItemData, in VIEW : RecyclerItemView<DATA>>
constructor(private val context: Context) : HeaderFooterRecyclerViewAdapter() {

    var headerData: DATA? = null
    var footerData: DATA? = null

    var headerView: View? = null
        private set
    var footerView: View? = null
        private set

    var items: MutableList<DATA>? = null
        set(value) {
            if (null != value)
                field = value
            notifyDataSetChanged()
        }

    override fun getHeaderItemCount(): Int {
        return if (null == headerView) 0 else 1
    }

    override fun getFooterItemCount(): Int {
        return if (null == footerView) 0 else 1
    }

    override fun getContentItemCount(): Int {
        return items!!.size
    }

    /**
     */

    override fun onCreateHeaderItemViewHolder(parent: ViewGroup, headerViewType: Int): RecyclerView.ViewHolder {
        return Header(headerView)
    }

    override fun onCreateFooterItemViewHolder(parent: ViewGroup, footerViewType: Int): RecyclerView.ViewHolder {
        return Header(footerView)
    }

    abstract override fun onCreateContentItemViewHolder(parent: ViewGroup, contentViewType: Int): RecyclerView.ViewHolder

    /**
     */

    override fun onBindHeaderItemViewHolder(headerViewHolder: RecyclerView.ViewHolder, position: Int) {
        if (headerViewHolder is RecyclerItemView<*> && null != headerData) {
            val itemView = headerViewHolder
            itemView.data = headerData
        }
    }

    override fun onBindFooterItemViewHolder(footerViewHolder: RecyclerView.ViewHolder, position: Int) {
        if (footerViewHolder is RecyclerItemView<*> && null != footerData) {
            val itemView = footerViewHolder
            itemView.data = footerData
        }
    }

    override fun onBindContentItemViewHolder(contentViewHolder: RecyclerView.ViewHolder, position: Int) {
        val itemView = contentViewHolder as VIEW
        if (0 < position)
            itemView.preData = items!![position - 1]
        else
            itemView.preData = null

        if (items!!.size > position + 1)
            itemView.afterData = items!![position + 1]
        else
            itemView.afterData = null

        itemView.index = position
        itemView.data = items!![position]
    }

    /**
     */

    fun add(data: DATA?) {
        if (null != data) {
            items!!.add(data)
            notifyDataSetChanged()
        }
    }

    fun add(index: Int, data: DATA?) {
        if (0 <= index && index <= items!!.size && null != data) {
            items!!.add(index, data)
            notifyDataSetChanged()
        }
    }

    fun addAll(items: List<DATA>?) {
        if (null != items && 0 < items.size && this.items!!.addAll(items))
            notifyDataSetChanged()
    }

    fun remove(item: DATA?) {
        if (null != item) {
            this.items!!.remove(item)
            notifyDataSetChanged()
        }
    }

    fun addHeaderView(headerView: View) {
        this.headerView = headerView
        notifyDataSetChanged()
    }

    fun addFooterView(footerView: View) {
        this.footerView = footerView
        notifyDataSetChanged()
    }

    fun removeHeaderView() {
        this.headerView = null
        notifyDataSetChanged()
    }

    fun removeFooterView() {
        this.footerView = null
        notifyDataSetChanged()
    }

    fun clear() {
        if (null != items) {
            this.items = ArrayList<DATA>()
            notifyDataSetChanged()
        }
    }

    fun notifyItemChanged(data: DATA?) {
        val index = items!!.indexOf(data)
        if (index < 0)
            return
        notifyContentItemChanged(index)
    }

    private class Header(itemView: View?) : RecyclerView.ViewHolder(itemView)

}

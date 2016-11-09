package com.moka.framework.widget.adapter


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import java.util.*

@Suppress("UNCHECKED_CAST")
abstract class BaseAdapter<DATA : ItemData, in VIEW : RecyclerItemView<DATA>>
constructor(private val context: Context) : HeaderFooterRecyclerViewAdapter(), IAdapterModel, IAdapterView {

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

    override fun getItemList(): MutableList<ItemData> {
        if (null != items)
            return items!! as MutableList<ItemData>
        else
            return ArrayList()
    }

    override fun add(data: ItemData?) {
        if (null != data) {
            items!!.add(data as DATA)
            notifyDataSetChanged()
        }
    }

    override fun add(index: Int, data: ItemData?) {
        if (0 <= index && index <= items!!.size && null != data) {
            items!!.add(index, data as DATA)
            notifyDataSetChanged()
        }
    }

    override fun addAll(items: List<ItemData>?) {
        if (null != items && 0 < items.size && this.items!!.addAll(items as List<DATA>))
            notifyDataSetChanged()
    }

    override fun remove(item: ItemData?) {
        if (null != item) {
            this.items!!.remove(item)
            notifyDataSetChanged()
        }
    }

    override fun addHeaderView(headerView: View) {
        this.headerView = headerView
        notifyDataSetChanged()
    }

    override fun addFooterView(footerView: View) {
        this.footerView = footerView
        notifyDataSetChanged()
    }

    override fun removeHeaderView() {
        this.headerView = null
        notifyDataSetChanged()
    }

    override fun removeFooterView() {
        this.footerView = null
        notifyDataSetChanged()
    }

    override fun clear() {
        if (null != items) {
            this.items = ArrayList<DATA>()
            notifyDataSetChanged()
        }
    }

    override fun notifyItemChanged(data: ItemData?) {
        val index = items!!.indexOf(data)
        if (index < 0)
            return
        notifyContentItemChanged(index)
    }

    override fun refresh() {
        notifyDataSetChanged()
    }

    private class Header(itemView: View?) : RecyclerView.ViewHolder(itemView)

}

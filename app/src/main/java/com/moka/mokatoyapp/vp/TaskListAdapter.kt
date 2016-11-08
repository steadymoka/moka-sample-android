package com.moka.mokatoyapp.vp


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.moka.framework.widget.adapter.BaseAdapter
import com.moka.framework.widget.adapter.ItemData
import com.moka.framework.widget.adapter.RecyclerItemView
import com.moka.mokatoyapp.R
import com.moka.mokatoyapp.model.domain.Task
import com.moka.mokatoyapp.vp.TaskListAdapter.TaskItemData
import com.moka.mokatoyapp.vp.TaskListAdapter.TaskItemView

class TaskListAdapter
constructor(private val context: Context) : BaseAdapter<TaskItemData, TaskItemView>(context) {

    var onItemClickListener: ((data: TaskItemData) -> Unit)? = null

    override fun onCreateContentItemViewHolder(parent: ViewGroup, contentViewType: Int): RecyclerView.ViewHolder {
        val itemView = TaskItemView(context, parent)
        itemView.onItemClickListener = onItemClickListener
        return itemView
    }

    /**
     * ItemData
     */

    class TaskItemData(var task: Task) : ItemData()

    /**
     * ItemView
     */

    class TaskItemView(context: Context, parent: ViewGroup) :
            RecyclerItemView<TaskItemData>(context, LayoutInflater.from(context).inflate(R.layout.task_item_view, parent, false)) {

        var onItemClickListener: ((data: TaskItemData) -> Unit)? = null

        init {
        }

        override fun refreshView(data: TaskItemData?) {
            if (null == data)
                return

        }

    }

}

package com.moka.mokatoyapp.vp.tasklist


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.moka.framework.widget.adapter.BaseAdapter
import com.moka.framework.widget.adapter.ItemData
import com.moka.framework.widget.adapter.RecyclerItemView
import com.moka.mokatoyapp.R
import com.moka.mokatoyapp.model.domain.Task
import com.moka.mokatoyapp.vp.tasklist.TaskListAdapter.TaskItemData
import com.moka.mokatoyapp.vp.tasklist.TaskListAdapter.TaskItemView
import kotlinx.android.synthetic.main.task_item_view.view.*

class TaskListAdapter
constructor(private val context: Context) : BaseAdapter<TaskItemData, TaskItemView>(context) {

    var onItemClickListener: ((data: TaskItemData) -> Unit)? = null
    var onItemCheckListener: ((data: TaskItemData) -> Unit)? = null

    override fun onCreateContentItemViewHolder(parent: ViewGroup, contentViewType: Int): RecyclerView.ViewHolder {
        val itemView = TaskItemView(context, parent)
        itemView.onItemClickListener = onItemClickListener
        itemView.onItemCheckListener = onItemCheckListener
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
        var onItemCheckListener: ((data: TaskItemData) -> Unit)? = null

        init {
            itemView.relativeLayout_container.setOnClickListener { if (null != onItemClickListener) onItemClickListener!!(data) }
            itemView.checkBox_complete.setOnClickListener { if (null != onItemCheckListener) onItemCheckListener!!(data) }
        }

        override fun refreshView(data: TaskItemData) {
            itemView.run {
                textView_title.text = data.task.title
                checkBox_complete.isChecked = data.task.completed
            }

        }

    }

}

package com.moka.mokatoyapp.vp

import com.moka.framework.base.BasePresenter
import com.moka.framework.widget.adapter.IAdapterModel
import com.moka.mokatoyapp.model.domain.Task
import com.moka.mokatoyapp.vp.TaskListAdapter.TaskItemData
import javax.inject.Inject

class TaskListPresenter
@Inject constructor(var adapterModel: IAdapterModel) : BasePresenter<TaskListView>() {

    fun aaa() {
        adapterModel.add(TaskItemData(Task()))
        adapterModel.add(TaskItemData(Task()))
    }

}
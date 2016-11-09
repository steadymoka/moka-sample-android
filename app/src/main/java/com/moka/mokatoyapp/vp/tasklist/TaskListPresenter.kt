package com.moka.mokatoyapp.vp.tasklist

import com.moka.framework.base.BasePresenter
import com.moka.framework.extenstion.put
import com.moka.framework.widget.adapter.IAdapterModel
import com.moka.mokatoyapp.model.domain.Task
import com.moka.mokatoyapp.vp.tasklist.TaskListAdapter.TaskItemData
import com.moka.mokatoyapp.vp.tasklist.TaskListFragment.Companion.ALL_TASKS
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject


class TaskListPresenter
@Inject constructor(var adapterModel: IAdapterModel) : BasePresenter<TaskListView>() {

    fun initModelObserver() {
        val observable = Task.ob.setOnChangeObservable()

        observable
                .filter { isAttached }
                .filter { it.isCreated }
                .subscribe({ data ->
                    adapterModel.add(TaskItemData(data.data))
                    view!!.refreshAdapterList()
                }, { e -> e.printStackTrace() }, {})
                .put(view!!.getCompositeSubscription())

        observable
                .filter { isAttached }
                .filter { it.isUpdated || it.isDeleted }
                .subscribe({ data ->
                    view!!.reloadTaskList()
                }, { e -> e.printStackTrace() }, {})
                .put(view!!.getCompositeSubscription())
    }

    /**
     */

    fun loadAllTasks() {
        loadTask(ALL_TASKS)
    }

    fun loadTask(filterStatus: Int) {
        adapterModel.clear()
        Task.getTasks(filterStatus)
                .observeOn(Schedulers.io())
                .filter { isAttached }
                .map { task -> adapterModel.getItemList().add(TaskItemData(task)) }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnCompleted {
                    if (adapterModel.getItemList().size > 0)
                        view!!.refreshAdapterList()
                    else
                        view!!.showNoTasks()
                }
                .subscribe({ }, { e -> e.printStackTrace() }, { })
    }

    fun completeTask(task: Task) {
        Task.update(task, { task ->
            task.completed = true
        })
        view!!.showTaskMarkedComplete()
    }

    fun activeTask(task: Task) {
        Task.update(task, { task ->
            task.completed = false
        })
        view!!.showTaskMarkedActive()
    }

}
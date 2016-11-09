package com.moka.mokatoyapp.vp.addedittask

import com.moka.framework.base.BasePresenter
import com.moka.mokatoyapp.model.domain.Task
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import javax.inject.Inject


class AddEditTaskPresenter
@Inject constructor() : BasePresenter<AddEditTaskView>() {

    private var task: Task? = null

    fun loadTask(taskId: Long) {
        Task.getTask(taskId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { task ->
                    if (!isAttached)
                        return@subscribe

                    this.task = task
                    view!!.setTitle(task.title)
                    view!!.setContent(task.content)
                }
    }

    fun createTask(title: String, content: String) {
        Task.insert { task ->
            task.title = title
            task.content = content
        }
        view!!.finishThisPage()
    }

    fun updateTask(title: String, content: String) {
        Task.update(task!!, { task ->
            task.title = title
            task.content = content
        })
        view!!.finishThisPage()
    }

    fun deleteTask(taskId: Long) {
        Task.delete(taskId)
        view!!.finishThisPage()
    }

}
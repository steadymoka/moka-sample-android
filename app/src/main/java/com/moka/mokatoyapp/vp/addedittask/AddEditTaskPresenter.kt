package com.moka.mokatoyapp.vp.addedittask

import com.moka.framework.base.BasePresenter
import com.moka.mokatoyapp.model.domain.Task
import com.moka.mokatoyapp.model.repository.ITaskRepository
import rx.android.schedulers.AndroidSchedulers
import javax.inject.Inject


class AddEditTaskPresenter
@Inject constructor(var taskRepository: ITaskRepository) : BasePresenter<AddEditTaskView>() {

    private var task: Task? = null

    fun loadTask(taskId: Long) {
        taskRepository.get(taskId)
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
        taskRepository.insert { task ->
            task.title = title
            task.content = content
        }
        view!!.finishThisPage()
    }

    fun updateTask(title: String, content: String) {
        taskRepository.update(task!!, { task ->
            task.title = title
            task.content = content
        })
        view!!.finishThisPage()
    }

    fun deleteTask(taskId: Long) {
        taskRepository.delete(taskId)
        view!!.finishThisPage()
    }

}
package com.moka.mokatoyapp.vp.tasklist

import com.moka.framework.base.BaseMvpView

interface TaskListView : BaseMvpView {

    fun refreshAdapterList()

    fun showTaskMarkedActive()

    fun showTaskMarkedComplete()

    fun reloadTaskList()

    fun showNoTasks()

}
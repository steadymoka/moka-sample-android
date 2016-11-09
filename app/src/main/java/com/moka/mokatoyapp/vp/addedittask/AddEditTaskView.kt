package com.moka.mokatoyapp.vp.addedittask

import com.moka.framework.base.BaseMvpView

interface AddEditTaskView : BaseMvpView {

    fun setTitle(title: String)

    fun setContent(content: String)

    fun finishThisPage()

}
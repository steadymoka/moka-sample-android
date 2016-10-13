package com.moka.framework.base


abstract class BasePresenter<VIEW : BaseMvpView> {

    var view: VIEW? = null
        private set

    fun attachView(view: VIEW) {
        this.view = view
    }

    open fun detachView() {
        view = null
    }

    val isAttached: Boolean
        get() = null != view

}

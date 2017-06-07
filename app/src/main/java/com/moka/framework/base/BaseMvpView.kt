package com.moka.framework.base


import rx.subscriptions.CompositeSubscription


interface BaseMvpView {

    fun showLoadingDialog()

    fun dismissLoadingDialog()

    fun getCompositeSubscription(): CompositeSubscription

}

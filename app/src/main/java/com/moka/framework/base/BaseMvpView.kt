package com.moka.framework.base


import android.app.Activity
import rx.subscriptions.CompositeSubscription


interface BaseMvpView {

    fun getActivity(): Activity

    fun showLoadingDialog()

    fun dismissLoadingDialog()

    fun isAdded(): Boolean

    fun getCompositeSubscription(): CompositeSubscription

}

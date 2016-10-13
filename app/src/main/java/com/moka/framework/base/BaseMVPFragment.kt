package com.moka.framework.base


import android.os.Bundle
import android.view.View


abstract class BaseMVPFragment : BaseFragment(), BaseMvpView {

    private var presenter: BasePresenter<BaseMVPFragment>? = null

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = getPresenter()
        if (null != presenter)
            presenter!!.attachView(this)
        onViewCreated_afterAttachViewToPresenter()
    }

    protected abstract fun onViewCreated_afterAttachViewToPresenter()

    override fun onDestroyView() {
        super.onDestroyView()
        if (null != presenter)
            presenter!!.detachView()
    }

    protected abstract fun getPresenter(): BasePresenter<BaseMVPFragment>

}

package com.moka.mokatoyapp.vp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.moka.framework.base.BaseMVPFragment
import com.moka.framework.base.BasePresenter
import com.moka.framework.extenstion.init
import com.moka.framework.widget.adapter.BaseAdapter
import com.moka.framework.widget.adapter.IAdapterView
import com.moka.framework.widget.toolbar.ToolbarLayout
import com.moka.mokatoyapp.MokaToyApplication
import com.moka.mokatoyapp.R
import com.moka.mokatoyapp._di.component.DaggerFragmentComponent
import com.moka.mokatoyapp._di.module.FragmentModule
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

class TaskListFragment : BaseMVPFragment(), TaskListView {

    @Inject
    lateinit var presenter: TaskListPresenter

    @Inject
    lateinit var adapterView: IAdapterView

    override fun getPresenter(): BasePresenter<*> = presenter

    /**
     * LifeCycle function
     */

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val rootView = inflater!!.inflate(R.layout.fragment_main, container, false)

        DaggerFragmentComponent.builder()
                .applicationComponent((activity.application as MokaToyApplication).applicationComponent)
                .fragmentModule(FragmentModule(activity, TaskListAdapter(activity) as BaseAdapter<*, *>))
                .build().inject(this)

        return rootView
    }

    override fun onViewCreated_afterAttachViewToPresenter() {
        recyclerView.init(activity, adapterView as TaskListAdapter)
        presenter.aaa()
    }

    /**
     */

    fun initToolbar(toolbarLayout: ToolbarLayout) {
        toolbarLayout.setHomeVisible(false)
    }

}
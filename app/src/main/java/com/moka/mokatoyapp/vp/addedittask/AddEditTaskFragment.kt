package com.moka.mokatoyapp.vp.addedittask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.moka.framework.base.BaseMvpFragment
import com.moka.framework.base.BasePresenter
import com.moka.mokatoyapp.MokaToyApplication
import com.moka.mokatoyapp.R
import com.moka.mokatoyapp._di.component.DaggerFragmentComponent
import com.moka.mokatoyapp._di.module.FragmentModule
import kotlinx.android.synthetic.main.fragment_task_add_edit.*
import javax.inject.Inject

class AddEditTaskFragment : BaseMvpFragment(), AddEditTaskView {

    companion object {
        val KEY_TASK_ID = "AddEditTaskFragment.KEY_TASK_ID"
    }

    /**
     * Injection value & needed mvp
     */

    @Inject
    lateinit var presenter: AddEditTaskPresenter

    override fun getPresenter(): BasePresenter<*> = presenter

    /**
     * LifeCycle function
     */

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val rootView = inflater!!.inflate(R.layout.fragment_task_add_edit, container, false)

        DaggerFragmentComponent.builder()
                .applicationComponent((activity.application as MokaToyApplication).applicationComponent)
                .fragmentModule(FragmentModule())
                .build().inject(this)

        return rootView
    }

    override fun onViewCreated_afterAttachViewToPresenter() {
        initToolbar()
        if (!isNewTask()) {
            toolbarLayout.setMenuText("수정")
            toolbarLayout.setToolbarTitle("Todo 수정")
            presenter.loadTask(activity.intent.getLongExtra(KEY_TASK_ID, -1))
        }
        else {
            toolbarLayout.setMenuText("등록")
            toolbarLayout.setToolbarTitle("Todo 등록")
            textView_deleteMessage.visibility = View.GONE
        }

        textView_deleteMessage.setOnClickListener { onClickToDeleteTask() }
    }

    /**
     * Implement MVP View Interface & View Event
     */

    override fun setTitle(title: String) {
        editText_title.setText(title)
    }

    override fun setContent(content: String) {
        editText_content.setText(content)
    }

    override fun finishThisPage() {
        activity.onBackPressed()
    }

    /* view Event */

    private fun onClickDoneMenu() {
        if (isNewTask())
            presenter.createTask(editText_title.text.toString(), editText_content.text.toString())
        else
            presenter.updateTask(editText_title.text.toString(), editText_content.text.toString())
    }

    private fun onClickToDeleteTask() {
        if (isNewTask())
            return
        presenter.deleteTask(activity.intent.getLongExtra(KEY_TASK_ID, -1))
    }

    /**
     */

    fun initToolbar() {
        toolbarLayout.setMenuListener { onClickDoneMenu() }
        toolbarLayout.setHomeListener { activity.onBackPressed() }
    }

    fun isNewTask(): Boolean {
        return activity.intent == null || activity.intent.getLongExtra(KEY_TASK_ID, -1) == -1.toLong()
    }

}
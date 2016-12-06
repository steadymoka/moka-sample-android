package com.moka.mokatoyapp.vp.tasklist

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.moka.framework.base.BaseMVPFragment
import com.moka.framework.base.BasePresenter
import com.moka.framework.extenstion.init
import com.moka.framework.extenstion.startOnAnim
import com.moka.framework.widget.adapter.IAdapterView
import com.moka.framework.widget.adapter.SimpleDecoration
import com.moka.framework.widget.toolbar.ToolbarLayout
import com.moka.mokatoyapp.MokaToyApplication
import com.moka.mokatoyapp.R
import com.moka.mokatoyapp._di.component.DaggerFragmentComponent
import com.moka.mokatoyapp._di.module.FragmentModule
import com.moka.mokatoyapp.vp.addedittask.AddEditTaskActivity
import com.moka.mokatoyapp.vp.addedittask.AddEditTaskFragment
import kotlinx.android.synthetic.main.fragment_task_list.*
import javax.inject.Inject

class TaskListFragment : BaseMVPFragment(), TaskListView {

    private var filterState: Int = 0

    companion object {
        val ALL_TASKS = 0
        val ACTIVE_TASKS = 1
        val COMPLETED_TASKS = 2
    }

    /**
     * Injection value & needed mvp
     */

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
        val rootView = inflater!!.inflate(R.layout.fragment_task_list, container, false)

        val adapter = TaskListAdapter(activity)
        adapter.setHasStableIds(true)
        adapter.onItemCheckListener = { onCheckListItem(it) }
        adapter.onItemClickListener = { onClickListItem(it) }

        DaggerFragmentComponent.builder()
                .applicationComponent((activity.application as MokaToyApplication).applicationComponent)
                .fragmentModule(FragmentModule(adapter))
                .build().inject(this)

        return rootView
    }

    override fun onViewCreated_afterAttachViewToPresenter() {
        initViewAndEvent()

        presenter.initModelObserver()
        presenter.loadAllTasks()
    }

    private fun initViewAndEvent() {
        recyclerView.init(activity, adapterView as TaskListAdapter)
        recyclerView.addItemDecoration(SimpleDecoration(activity))
        floatingActionButton_add.setOnClickListener { onClickFloatingActionButton() }
    }

    /**
     * Implement MVP View Interface & View Event
     */

    override fun reloadTaskList() {
        presenter.loadTask(filterState)
    }

    override fun refreshAdapterList() {
        recyclerView.visibility = View.VISIBLE
        linearLayout_noTasks.visibility = View.GONE
        adapterView.refresh()
    }

    override fun showNoTasks() {
        recyclerView.visibility = View.GONE
        linearLayout_noTasks.visibility = View.VISIBLE

        var mainText = resources.getString(R.string.no_tasks_all)
        var iconRes = R.drawable.ic_assignment_turned_in_24dp
        var showAddView = true
        if (filterState == ACTIVE_TASKS) {
            mainText = resources.getString(R.string.no_tasks_active)
            iconRes = R.drawable.ic_check_circle_24dp
            showAddView = false
        }
        else if (filterState == COMPLETED_TASKS) {
            mainText = resources.getString(R.string.no_tasks_completed)
            iconRes = R.drawable.ic_verified_user_24dp
            showAddView = false
        }
        textView_noTasksMain.text = mainText
        imageView_noTasksIcon.setImageResource(iconRes)
        textView_noTasksAdd.visibility = if (showAddView) View.VISIBLE else View.GONE
    }

    override fun showTaskMarkedActive() {
        Snackbar.make(floatingActionButton_add, getString(R.string.task_marked_active), Snackbar.LENGTH_SHORT).show()
    }

    override fun showTaskMarkedComplete() {
        Snackbar.make(floatingActionButton_add, getString(R.string.task_marked_complete), Snackbar.LENGTH_SHORT).show()
    }

    /* View Event */

    private fun onClickFloatingActionButton() {
        activity.startOnAnim(AddEditTaskActivity::class.java)
    }

    private fun onCheckListItem(itemData: TaskListAdapter.TaskItemData) {
        if (!itemData.task.completed)
            presenter.completeTask(itemData.task)
        else
            presenter.activeTask(itemData.task)
    }

    private fun onClickListItem(itemData: TaskListAdapter.TaskItemData) {
        val intent = Intent(activity, AddEditTaskActivity::class.java)
        intent.putExtra(AddEditTaskFragment.KEY_TASK_ID, itemData.task.id)
        activity.startOnAnim(intent)
    }

    private fun onClickToSetFilter() {
        FilterDialogFragment.newInstance()
                .setSelectedFilter(filterState)
                .showDialog(fragmentManager, { filterStatus ->
                    this.filterState = filterStatus
                    setFilterLabel(filterState)
                    presenter.loadTask(filterStatus)
                })
    }

    /**
     */

    fun setFilterLabel(filterState: Int) {
        when (filterState) {
            ALL_TASKS -> textView_filteringLabel.text = "ALL Tasks _"
            ACTIVE_TASKS -> textView_filteringLabel.text = "Active Tasks _"
            COMPLETED_TASKS -> textView_filteringLabel.text = "Completed Tasks _"
        }
    }

    fun initToolbar(toolbarLayout: ToolbarLayout) {
        toolbarLayout.setHomeVisible(false)
        toolbarLayout.setMenuIcon(R.drawable.ic_filter_list)
        toolbarLayout.setMenuListener { onClickToSetFilter() }
    }

}
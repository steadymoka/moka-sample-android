package com.moka.mokatoyapp.vp.tasklist


import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.moka.framework.base.BaseDialogFragment
import com.moka.mokatoyapp.R
import com.moka.mokatoyapp.vp.tasklist.TaskListFragment.Companion.ACTIVE_TASKS
import com.moka.mokatoyapp.vp.tasklist.TaskListFragment.Companion.ALL_TASKS
import com.moka.mokatoyapp.vp.tasklist.TaskListFragment.Companion.COMPLETED_TASKS
import kotlinx.android.synthetic.main.dialog_filter_taske.*


class FilterDialogFragment() : BaseDialogFragment() {

    private var currentFilter: Int = 0

    private lateinit var onFilterSelected: ((Int) -> Unit)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.dialog_filter_taske, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        initFilterCheckImage(currentFilter)
        setEvent()
    }

    private fun initFilterCheckImage(currentFilter: Int) {
        when (currentFilter) {
            ALL_TASKS -> imageView_allTasks.visibility = View.VISIBLE
            ACTIVE_TASKS -> imageView_activeTasks.visibility = View.VISIBLE
            COMPLETED_TASKS -> imageView_completedTasks.visibility = View.VISIBLE
        }
    }

    private fun setEvent() {
        textView_allTasks.setOnClickListener {
            onFilterSelected(ALL_TASKS)
            dismiss()
        }
        textView_activeTasks.setOnClickListener {
            onFilterSelected(ACTIVE_TASKS)
            dismiss()
        }
        textView_completedTasks.setOnClickListener {
            onFilterSelected(COMPLETED_TASKS)
            dismiss()
        }
    }

    /**
     */

    fun setSelectedFilter(currentFilter: Int): FilterDialogFragment {
        this.currentFilter = currentFilter
        return this
    }

    fun showDialog(manager: FragmentManager, onDialogButtonClickListener: (Int) -> Unit) {
        this.onFilterSelected = onDialogButtonClickListener
        show(manager, "DialogFragment")
    }

    /**
     * Interface implement
     */

    companion object Factory {

        fun newInstance(): FilterDialogFragment = FilterDialogFragment()

    }

}

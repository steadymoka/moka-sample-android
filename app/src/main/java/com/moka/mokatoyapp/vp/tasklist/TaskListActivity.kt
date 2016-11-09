package com.moka.mokatoyapp.vp.tasklist


import android.os.Bundle
import com.moka.framework.base.BaseActivity
import com.moka.mokatoyapp.R
import kotlinx.android.synthetic.main.activity_toolbar.*


class TaskListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toolbar)

        val taskListFragment: TaskListFragment = TaskListFragment()
        taskListFragment.initToolbar(toolbarLayout)
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.frameLayout_container, taskListFragment)
                .commit()
    }

}

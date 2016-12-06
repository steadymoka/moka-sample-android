package com.moka.mokatoyapp.vp.tasklist


import android.os.Bundle
import com.moka.framework.base.BaseActivity
import com.moka.framework.util.MLog
import com.moka.mokatoyapp.MokaPreference
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

        checkFirstRun()
    }

    private fun checkFirstRun() {
        if (MokaPreference.getInstance().isFirstRun) {
            MLog.deb("isFirst")

            MokaPreference.getInstance().isFirstRun = false
        }
        else
            MLog.deb("is Not First")
    }

}

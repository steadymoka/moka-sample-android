package com.moka.mokatoyapp.vp.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.moka.framework.base.BaseFragment
import com.moka.framework.extenstion.init
import com.moka.framework.widget.adapter.SimpleDecoration
import com.moka.mokatoyapp.R
import com.moka.mokatoyapp.model.repository.TaskRepository
import com.moka.mokatoyapp.vp.tasklist.TaskListAdapter
import kotlinx.android.synthetic.main.fragment_test.*
import org.jetbrains.anko.support.v4.act
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class TestFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_test, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TaskListAdapter(act)
        adapter.setHasStableIds(true)

        recyclerView.init(act, adapter)
        recyclerView.addItemDecoration(SimpleDecoration(act))

        TaskRepository()
                .getTasks(10000)
                .observeOn(Schedulers.io())
                .filter { true }
                .map { task -> adapter.getItemList().add(TaskListAdapter.TaskItemData(task)) }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnCompleted {
                    if (adapter.getItemList().size > 0)
                        adapter.notifyDataSetChanged()
                }
                .subscribe({ }, Throwable::printStackTrace, { })

        appBarlayout.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            test.alpha = (verticalOffset.toFloat() / appBarLayout.totalScrollRange) * -1
        }
    }
}
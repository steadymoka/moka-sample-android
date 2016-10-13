package com.moka.framework.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.moka.framework.util.MLog
import rx.subscriptions.CompositeSubscription

abstract class BaseFragment : Fragment() {

    private var TAG = javaClass.simpleName
    private var compositeSubscription: CompositeSubscription? = null

    /**
     * LifeCycle method implement
     */

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        MLog.a("lifeCycle", TAG + " onCreateView called ####")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MLog.a("lifeCycle", TAG + " onViewCreated called ####")
    }

    override fun onStart() {
        super.onStart()
        MLog.a("lifeCycle", TAG + "  onStart called ####")
    }

    /** ###### onPause  */
    override fun onResume() {
        super.onResume()
        MLog.a("lifeCycle", TAG + " onResume called ####")
    }

    /** ###### onResume  */
    override fun onPause() {
        super.onPause()
        MLog.a("lifeCycle", TAG + "  onPause called ####")
    }

    override fun onStop() {
        super.onStop()
        MLog.a("lifeCycle", TAG + "   onStop called ####")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        MLog.a("lifeCycle", TAG + " onDestroyView called ####")

        if (null != compositeSubscription)
            compositeSubscription!!.clear()
    }

}
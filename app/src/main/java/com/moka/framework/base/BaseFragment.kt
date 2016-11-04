package com.moka.framework.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
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

    /** **/

    fun getCompositeSubscription(): CompositeSubscription {
        if (null == compositeSubscription)
            compositeSubscription = CompositeSubscription()
        return compositeSubscription!!
    }

    fun dismissSoftKeyOnTouch(rootView: View) {
        rootView.setOnTouchListener { view, motionEvent ->
            hideSoftKey()
            false
        }
    }

    fun hideSoftKey() {
        val inputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val currentFocus = activity.currentFocus
        if (null != currentFocus)
            inputMethodManager.hideSoftInputFromWindow(currentFocus.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }

}
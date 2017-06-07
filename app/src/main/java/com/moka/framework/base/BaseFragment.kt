package com.moka.framework.base

import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.moka.framework.extenstion.hideSoftKey
import com.moka.framework.util.MLog
import com.moka.mokatoyapp.R
import org.jetbrains.anko.support.v4.act
import rx.subscriptions.CompositeSubscription

abstract class BaseFragment : Fragment() {

    private var TAG = javaClass.simpleName
    private var compositeSubscription: CompositeSubscription? = null
    private var loadingDialog: AlertDialog? = null

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
            hideSoftKey(act)
            false
        }
    }

    /**
     * About LoadingDailog
     */

    private fun getLoadingDialog(): AlertDialog {
        if (null == loadingDialog) {
            val builder = AlertDialog.Builder(act)
            val rootView = act.layoutInflater.inflate(R.layout.dialog_loading, null)
            builder.setView(rootView)
            builder.setCancelable(false)

            loadingDialog = builder.create()
        }

        return loadingDialog!!
    }

    fun showLoadingDialog() {
        if (!getLoadingDialog().isShowing && isAdded)
            loadingDialog!!.show()
    }

    fun dismissLoadingDialog() {
        if (null != loadingDialog && loadingDialog!!.isShowing)
            loadingDialog!!.dismiss()
    }

}
package com.moka.framework.base


import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


open class BaseDialogFragment : AppCompatDialogFragment() {

    private var onDismissDialogListener: OnDismissDialogListener? = null

    /**
     * Dialog LifeCycle
     * onCreate -> onCreateDialog -> onCreateView -> onViewCreated -> onResume
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        setStyle(DialogFragment.STYLE_NO_TITLE, 0)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }

    /**
     */

    private val baseActivity: BaseActivity?
        get() {
            val activity = activity

            if (activity is BaseActivity)
                return activity
            else
                return null
        }

    override fun onDismiss(dialog: DialogInterface?) {
        super.onDismiss(dialog)

        if (null != onDismissDialogListener)
            onDismissDialogListener!!.onDismiss()
    }

    fun setOnDismissDialogListener(onDismissDialogListener: OnDismissDialogListener) {
        this.onDismissDialogListener = onDismissDialogListener
    }

    interface OnDismissDialogListener {

        fun onDismiss()

    }

}

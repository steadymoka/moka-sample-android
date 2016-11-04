package com.moka.framework.widget.dialog


import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.moka.framework.base.BaseDialogFragment
import com.moka.mokatoyapp.R
import kotlinx.android.synthetic.main.dialog_no_button_alert.*


class AlertDialogNoButtonFragment : BaseDialogFragment() {

    private var onAlertListener: OnAlertListener? = null
    private var callback: ((isOk: Boolean) -> Unit)? = null

    private var iconResId = 0
    private var message: CharSequence = ""
    private var messageAlign: Int = Gravity.CENTER
    private var okText = ""
    private var cancelText = ""

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.dialog_no_button_alert, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

        button_cancel.setOnClickListener { onClickCancel() }
        button_ok.setOnClickListener { onClickOk() }
    }

    private fun initView() {
        if (iconResId == 0)
            imageView_icon.visibility = View.GONE
        else {
            imageView_icon.setImageResource(iconResId)
        }

        if (!message.toString().isEmpty())
            textView_message.text = message

        textView_message.gravity = messageAlign

        if (!okText.isEmpty())
            button_ok.text = okText

        if (!cancelText.isEmpty())
            button_cancel.text = cancelText
    }

    /**
     */

    fun onClickCancel() {
        onAlertListener?.onClick(false)
        if (null != callback)
            callback!!(false)
        dismiss()
    }

    fun onClickOk() {
        onAlertListener?.onClick(true)
        if (null != callback)
            callback!!(true)
        dismiss()
    }

    /**
     */

    fun showDialog(fragmentManager: FragmentManager, onAlertListener: OnAlertListener) {
        this.onAlertListener = onAlertListener
        show(fragmentManager, "AlertDialogNoButtonFragment")
    }

    fun showDialog(fragmentManager: FragmentManager, callback: (Boolean) -> Unit) {
        this.callback = callback
        show(fragmentManager, "AlertDialogNoButtonFragment")
    }

    fun setIconResId(iconResId: Int): AlertDialogNoButtonFragment {
        this.iconResId = iconResId
        return this
    }

    fun setMessage(message: CharSequence? = ""): AlertDialogNoButtonFragment {
        this.message = message!!
        return this
    }

    fun setOkText(okText: String? = ""): AlertDialogNoButtonFragment {
        this.okText = okText!!
        return this
    }

    fun setMessageAlign(messageAlign: Int = Gravity.CENTER): AlertDialogNoButtonFragment {
        this.messageAlign = messageAlign
        return this
    }

    fun setButtonTextSizeToDp(textSizeDp: Int): AlertDialogNoButtonFragment {
        // TODO : textSize 멤버로 가지고 있다가 세팅 해주는것 필요
        return this
    }

    fun setIsCancelVisible(isCancelVisible: Boolean): AlertDialogNoButtonFragment {
        if (!isCancelVisible)
            button_cancel.visibility = View.GONE

        return this
    }

    fun setCancelText(cancelText: String? = ""): AlertDialogNoButtonFragment {
        this.cancelText = cancelText!!
        return this
    }

    companion object Factory {

        fun newInstance(): AlertDialogNoButtonFragment = AlertDialogNoButtonFragment()

    }

    interface OnAlertListener {

        fun onClick(isOk: Boolean)

    }

}

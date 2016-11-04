package com.moka.framework.widget.toolbar


import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.moka.gdgqrr.R
import com.nineoldandroids.view.ViewHelper


class ToolbarLayout : FrameLayout {

    private var rootViewOfToolbarLayout: View? = null
    private var toolbar_frameLayout_content: FrameLayout? = null
    private var imageView_home: ImageView? = null
    private var imageView_menu: ImageView? = null
    private var textView_menu: TextView? = null
    private var textView_toolbarTitle: TextView? = null
    private var frameLayout_toolbarBackground: FrameLayout? = null
    private var view_shadow: View? = null
    private var relativeLayout_toolbar: RelativeLayout? = null

    private var homeButtonListener: (() -> Unit?)? = null
    private var menuButtonListener: (() -> Unit?)? = null

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView()
    }

    private fun initView() {
        rootViewOfToolbarLayout = LayoutInflater.from(context).inflate(R.layout.toolbar_moka, null)

        toolbar_frameLayout_content = rootViewOfToolbarLayout?.findViewById(R.id.toolbar_frameLayout_content) as FrameLayout?
        imageView_home = rootViewOfToolbarLayout?.findViewById(R.id.imageView_home) as ImageView?
        imageView_menu = rootViewOfToolbarLayout?.findViewById(R.id.imageView_menu) as ImageView?
        textView_menu = rootViewOfToolbarLayout?.findViewById(R.id.textView_menu) as TextView?
        textView_toolbarTitle = rootViewOfToolbarLayout?.findViewById(R.id.textView_toolbarTitle) as TextView?
        frameLayout_toolbarBackground = rootViewOfToolbarLayout?.findViewById(R.id.frameLayout_toolbarBackground) as FrameLayout?
        view_shadow = rootViewOfToolbarLayout?.findViewById(R.id.view_shadow)
        view_shadow?.bringToFront()
        relativeLayout_toolbar = rootViewOfToolbarLayout?.findViewById(R.id.relativeLayout_toolbar) as RelativeLayout?

        imageView_home?.setOnClickListener { if (null != homeButtonListener) homeButtonListener!!() }
        imageView_menu?.setOnClickListener { if (null != menuButtonListener) menuButtonListener!!() }

        addView(rootViewOfToolbarLayout!!)
    }

    override fun addView(child: View) {
        if (canAddChildView(child))
            addViewToContentView(child)
    }

    private fun canAddChildView(child: View): Boolean {
        if (rootViewOfToolbarLayout === child) {

            super.addView(child, -1, generateMatchParentLayoutParams())
            return false
        }
        else if (null != toolbar_frameLayout_content && (toolbar_frameLayout_content as FrameLayout).childCount > 0) {

            throw IllegalStateException("ToolbarLayout can host only one direct child")
        }

        return true
    }

    private fun generateMatchParentLayoutParams(): FrameLayout.LayoutParams {
        return FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    private fun addViewToContentView(child: View) {
        val layoutParams = generateMatchParentLayoutParams()
        child.layoutParams = layoutParams
        toolbar_frameLayout_content?.addView(child)
    }

    override fun addView(child: View, index: Int) {
        if (canAddChildView(child))
            addViewToContentView(child)
    }

    override fun addView(child: View, width: Int, height: Int) {
        if (canAddChildView(child))
            addViewToContentView(child)
    }

    override fun addView(child: View, params: ViewGroup.LayoutParams) {
        if (canAddChildView(child))
            addViewToContentView(child)
    }

    override fun addView(child: View, index: Int, params: ViewGroup.LayoutParams) {
        if (canAddChildView(child))
            addViewToContentView(child)
    }

    /**
     * 홈버튼, 메뉴버튼
     */

    fun setHomeIcon(resId: Int) {
        imageView_home?.setImageResource(resId)
    }

    fun setMenuIcon(resId: Int) {
        imageView_menu?.setImageResource(resId)
    }

    fun setMenuText(menuText: String) {
        textView_menu?.text = menuText
    }

    fun setHomeVisible(isVisible: Boolean) {
        if (isVisible)
            imageView_home?.visibility = View.VISIBLE
        else
            imageView_home?.visibility = View.GONE
    }

    fun setMenuVisible(isVisible: Boolean) {
        if (isVisible) {
            textView_menu?.visibility = View.VISIBLE
            imageView_menu?.visibility = View.VISIBLE
        }
        else {
            textView_menu?.visibility = View.GONE
            imageView_menu?.visibility = View.GONE
        }
    }

    fun setHomeListener(homeListener: () -> Unit) {
        this.homeButtonListener = homeListener
    }

    fun setMenuListener(menuListener: () -> Unit) {
        this.menuButtonListener = menuListener
    }

    /**
     * About Toolbar Title
     */

    fun setToolbarTitle(title: CharSequence) {
        textView_toolbarTitle?.text = title
    }

    fun setToolbarTitleColor(color: Int) {
        textView_toolbarTitle?.setTextColor(color)
    }

    fun setToolbarTitleSize(sizeDP: Int) {
        textView_toolbarTitle?.setTextSize(TypedValue.COMPLEX_UNIT_DIP, sizeDP.toFloat())
    }

    /**
     * About Toolbar
     */

    fun setToolbarEnable(isEnable: Boolean) {
        if (isEnable) {
            imageView_home?.visibility = View.VISIBLE
            imageView_menu?.visibility = View.VISIBLE
        }
        else {
            imageView_home?.visibility = View.GONE
            imageView_menu?.visibility = View.GONE
        }
    }

    fun setToolbarBackground(colorRes: Int) {
        frameLayout_toolbarBackground?.setBackgroundResource(colorRes)
    }

    override fun setAlpha(alpha: Float) {
        ViewHelper.setAlpha(frameLayout_toolbarBackground, alpha)
        ViewHelper.setAlpha(view_shadow, alpha)
        textView_toolbarTitle?.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15f)
        if (alpha == 0f)
            setExpand(true)
        else
            setExpand(false)
    }

    fun setExpand(expand: Boolean) {
        val context = context

        if (null != context) {
            val layoutParams = toolbar_frameLayout_content?.layoutParams as RelativeLayout.LayoutParams

            if (expand) {
                layoutParams.topMargin = 0
            }
            else {
                val array = context.theme.obtainStyledAttributes(intArrayOf(R.attr.actionBarSize))
                layoutParams.topMargin = array.getDimensionPixelSize(0, 0)
                array.recycle()
            }

            toolbar_frameLayout_content?.layoutParams = layoutParams
        }
    }

    fun toolBarToLonely(isLonely: Boolean) {
        if (isLonely) {
            toolbar_frameLayout_content?.visibility = View.GONE
            view_shadow?.visibility = View.GONE
        }
        else {
            toolbar_frameLayout_content?.visibility = View.VISIBLE
            view_shadow?.visibility = View.VISIBLE
        }
    }

}

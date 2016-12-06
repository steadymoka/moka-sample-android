package com.moka.framework.widget.adapter

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import com.moka.framework.util.ScreenUtil
import com.moka.mokatoyapp.R


class SimpleDecoration(var context: Context, var mMargin: Int? = null) : RecyclerView.ItemDecoration() {

    private val mDivider: Drawable

    init {
        mDivider = ContextCompat.getDrawable(context, R.drawable.line_divider)
        if (mMargin == null)
            mMargin = ScreenUtil.dipToPixel(context, 24.0).toInt()
        else
            mMargin = ScreenUtil.dipToPixel(context, mMargin!!.toDouble()).toInt()
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft + mMargin!!
        val right = parent.width - parent.paddingRight - mMargin!!

        val childCount = parent.childCount
        for (i in 0..childCount - 1) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin
            val bottom = top + mDivider.intrinsicHeight

            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(c)
        }
    }

}
package com.moka.framework.util


import android.content.Context
import android.util.DisplayMetrics
import android.view.View
import com.moka.mokatoyapp.MokaToyApplication
import com.moka.mokatoyapp.R


class ScreenUtil {

    companion object {

        private val context = MokaToyApplication.context
        private var displayMetrics: DisplayMetrics? = null

        fun dipToPixel(context: Context, dip: Double): Double {
            return dip * getDisplayMetrics(context).densityDpi / 160.0
        }

        fun pixelToDip(context: Context, pixel: Double): Double {
            return pixel * 160.0 / getDisplayMetrics(context).densityDpi
        }

        private fun getDisplayMetrics(context: Context): DisplayMetrics {
            if (null == displayMetrics) {

                try {
                    displayMetrics = context.resources.displayMetrics
                } catch (e: NullPointerException) {
                    displayMetrics = ScreenUtil.context.resources.displayMetrics
                }
            }
            return displayMetrics!!
        }

        fun getWidthPixels(context: Context): Int {
            return getDisplayMetrics(context).widthPixels
        }

        fun getHeightPixels(context: Context): Int {
            return getDisplayMetrics(context).heightPixels
        }

        val statusBarSize: Int
            get() {

                val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")

                if (resourceId > 0)
                    return context.resources.getDimensionPixelSize(resourceId)
                else
                    return 19
            }

        val toolBarSize: Int
            get() = context.resources.getDimensionPixelSize(R.dimen.abc_action_bar_default_height_material)

        fun getScreenRatio(context: Context): Float {
            val width = getWidthPixels(context).toFloat()
            val height = getHeightPixels(context).toFloat()

            return width / height
        }
    }

}

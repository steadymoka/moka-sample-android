package com.moka.framework.util


import android.content.Context
import android.util.DisplayMetrics
import android.view.View
import com.moka.mokatoyapp.MokaToyApplication
import com.moka.mokatoyapp.R


class ScreenUtil {

    private fun getStatusBarSizedd() {

        //		Rect rectgle = new Rect();
        //		Window window = activity.getWindow();
        //		window.getDecorView().getWindowVisibleDisplayFrame( rectgle );
        //		int StatusBarHeight = rectgle.top;
        //		int contentViewTop =
        //				window.findViewById( Window.ID_ANDROID_CONTENT ).getTop();
        //		int TitleBarHeight = contentViewTop - StatusBarHeight;
    }

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

                val resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android")

                if (resourceId > 0)
                    return context.getResources().getDimensionPixelSize(resourceId)
                else
                    return 19
            }

        fun setPaddingTopStatusBarSizeTo(view: View) {
            view.setPadding(0, statusBarSize, 0, 0)
        }

        fun setMarginTopStatusBarSizeTo(view: View) {

        }

        val toolBarSize: Int
            get() = context.getResources().getDimensionPixelSize(R.dimen.abc_action_bar_default_height_material)

        fun getScreenRatio(context: Context): Float {
            val width = getWidthPixels(context).toFloat()
            val height = getHeightPixels(context).toFloat()

            return width / height
        }
    }

}

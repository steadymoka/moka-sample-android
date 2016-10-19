package com.moka.framework.stickerview.util


import android.content.Context
import android.graphics.*
import android.support.v4.content.ContextCompat
import com.moka.framework.stickerview.MyHighlightView
import com.moka.framework.stickerview.MyImageViewDrawableOverlay
import com.moka.framework.stickerview.drawable.StickerDrawable
import com.moka.framework.stickerview.imagezoom.ImageViewTouch
import com.moka.framework.extenstion.asBitmap
import com.moka.framework.stickerview.util.MatrixUtils
import com.moka.framework.util.ScreenUtil
import com.moka.mokatoyapp.AppConstants
import com.moka.mokatoyapp.MokaToyApplication
import com.moka.mokatoyapp.R
import java.util.*
import java.util.concurrent.CopyOnWriteArrayList


object EffectUtil {

    private val hightlistViews = CopyOnWriteArrayList<MyHighlightView>()
    private var timeHvView: MyHighlightView? = null

    fun clear() {
        hightlistViews.clear()
    }

    fun addTimeSticker(processImage: ImageViewTouch, context: Context, bitmap: Bitmap): MyHighlightView? {
        val stickerBitmap: Bitmap
        stickerBitmap = bitmap

        val drawable = StickerDrawable(context.resources, stickerBitmap)
        drawable.setAntiAlias(true)
        drawable.setDropShadow(false)
        drawable.setMinSize(20f, 20f)

        val hv = MyHighlightView(processImage, R.style.AppTheme, drawable)
        hv.setPadding(4)
        hv.setDeleteable(false)

        val mImageMatrix = processImage.imageViewMatrix

        var cropWidth: Int
        var cropHeight: Int
        var x: Int
        var y: Int

        val width = processImage.width
        val height = processImage.height

        // width/height of the sticker
        cropWidth = drawable.currentWidth.toInt()
        cropHeight = drawable.currentHeight.toInt()

        val cropSize = Math.max(cropWidth, cropHeight)
        val screenSize = Math.min(processImage.width, processImage.height)
        var positionRect: RectF? = null
        if (cropSize > screenSize) {
            val ratio: Float
            val widthRatio = processImage.width.toFloat() / cropWidth
            val heightRatio = processImage.height.toFloat() / cropHeight

            if (widthRatio < heightRatio) {
                ratio = widthRatio
            }
            else {
                ratio = heightRatio
            }

            cropWidth = (cropWidth.toFloat() * (ratio / 2)).toInt()
            cropHeight = (cropHeight.toFloat() * (ratio / 2)).toInt()

            val w = processImage.width
            val h = processImage.height
            positionRect = RectF((w / 2 - cropWidth / 2).toFloat(), (h / 2 - cropHeight / 2).toFloat(),
                    (w / 2 + cropWidth / 2).toFloat(), (h / 2 + cropHeight / 2).toFloat())

            positionRect.inset((positionRect.width() - cropWidth) / 2,
                    (positionRect.height() - cropHeight) / 2)
        }

        if (positionRect != null) {
            x = positionRect.left.toInt()
            y = positionRect.top.toInt()

        }
        else {
            x = (width - cropWidth) / 2
            y = (height - cropHeight) / 2
        }
        y = ((height - cropHeight) / 4).toInt()

        timeHvView = hv

        val matrix = Matrix(mImageMatrix)
        matrix.invert(matrix)

        val pts = floatArrayOf(x.toFloat(), y.toFloat(), (x + cropWidth).toFloat(), (y + cropHeight).toFloat())
        MatrixUtils.mapPoints(matrix, pts)

        val cropRect = RectF(pts[0], pts[1], pts[2], pts[3])
        val imageRect = Rect(0, 0, width, height)

        hv.setup(context, mImageMatrix, imageRect, cropRect, false)

        (processImage as MyImageViewDrawableOverlay).addHighlightView(hv)
        processImage.selectedHighlightView = hv
        hightlistViews.add(hv)
        return hv
    }

    fun addLogoSticker(processImage: ImageViewTouch, context: Context, bitmap: Bitmap, location: String = "bottomCenter"): MyHighlightView? {
        val stickerBitmap: Bitmap
        stickerBitmap = bitmap

        val drawable = StickerDrawable(context.resources, stickerBitmap)
        drawable.setAntiAlias(true)
        drawable.setDropShadow(false)
        drawable.setMinSize(20f, 20f)

        val hv = MyHighlightView(processImage, R.style.AppTheme, drawable)
        hv.setPadding(4)
        hv.setDeleteable(false)
        hv.setScaleable(false)
        hv.setMoveable(false)

        val mImageMatrix = processImage.imageViewMatrix

        var cropWidth: Int
        var cropHeight: Int
        var x: Int
        var y: Int

        val width = processImage.width
        val height = processImage.height

        // width/height of the sticker
        cropWidth = drawable.currentWidth.toInt()
        cropHeight = drawable.currentHeight.toInt()

        val cropSize = Math.max(cropWidth, cropHeight)
        val screenSize = Math.min(processImage.width, processImage.height)
        var positionRect: RectF? = null
        if (cropSize > screenSize) {
            val ratio: Float
            val widthRatio = processImage.width.toFloat() / cropWidth
            val heightRatio = processImage.height.toFloat() / cropHeight

            if (widthRatio < heightRatio) {
                ratio = widthRatio
            }
            else {
                ratio = heightRatio
            }

            cropWidth = (cropWidth.toFloat() * (ratio / 2)).toInt()
            cropHeight = (cropHeight.toFloat() * (ratio / 2)).toInt()

            val w = processImage.width
            val h = processImage.height
            positionRect = RectF((w / 2 - cropWidth / 2).toFloat(), (h / 2 - cropHeight / 2).toFloat(),
                    (w / 2 + cropWidth / 2).toFloat(), (h / 2 + cropHeight / 2).toFloat())

            positionRect.inset((positionRect.width() - cropWidth) / 2,
                    (positionRect.height() - cropHeight) / 2)
        }

        when (location) {
            "bottomCenter" -> {
                x = (width - cropWidth) / 2
                y = height - cropHeight - 25
            }
            "bottomRight" -> {
                x = (width - cropWidth) - 25
                y = height - cropHeight - 25
            }
            "topLeft" -> {
                x = 25
                y = 30
            }
            else -> {
                x = (width - cropWidth) / 2
                y = height - cropHeight - 25
            }
        }

        val matrix = Matrix(mImageMatrix)
        matrix.invert(matrix)

        val pts = floatArrayOf(x.toFloat(), y.toFloat(), (x + cropWidth).toFloat(), (y + cropHeight).toFloat())
        MatrixUtils.mapPoints(matrix, pts)

        val cropRect = RectF(pts[0], pts[1], pts[2], pts[3])
        val imageRect = Rect(0, 0, width, height)

        hv.setup(context, mImageMatrix, imageRect, cropRect, false)

        (processImage as MyImageViewDrawableOverlay).addHighlightView(hv)
        processImage.selectedHighlightView = hv
        hightlistViews.add(hv)
        return hv
    }

    fun removeDateHv(processImage: ImageViewTouch) {
        (processImage as MyImageViewDrawableOverlay).removeHightlightView(timeHvView)
        hightlistViews.remove(timeHvView)
        processImage.invalidate()
    }

    /**
     */

    fun applyOnSave(mCanvas: Canvas, processImage: ImageViewTouch) {
        for (view in hightlistViews) {
            applyOnSave(mCanvas, processImage, view)
        }
    }

    private fun applyOnSave(mCanvas: Canvas, processImage: ImageViewTouch, view: MyHighlightView?) {
        if (view != null && view.content is StickerDrawable) {

            val stickerDrawable = view.content as StickerDrawable
            val cropRect = view.cropRectF
            val rect = Rect(cropRect.left.toInt(), cropRect.top.toInt(), cropRect.right.toInt(),
                    cropRect.bottom.toInt())

            val rotateMatrix = view.cropRotationMatrix
            val matrix = Matrix(processImage.imageMatrix)
            if (!matrix.invert(matrix)) {
            }
            val saveCount = mCanvas.save(Canvas.MATRIX_SAVE_FLAG)
            mCanvas.concat(rotateMatrix)

            stickerDrawable.setDropShadow(false)
            view.content.bounds = rect
            view.content.draw(mCanvas)
            mCanvas.restoreToCount(saveCount)
        }
    }

}

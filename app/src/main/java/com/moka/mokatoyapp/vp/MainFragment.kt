package com.moka.mokatoyapp.vp

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.RectF
import android.net.Uri
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import com.moka.framework.base.BaseFragment
import com.moka.framework.extenstion.asBitmap
import com.moka.framework.extenstion.workInBack
import com.moka.framework.stickerview.MyImageViewDrawableOverlay
import com.moka.framework.stickerview.util.EffectUtil
import com.moka.framework.util.*
import com.moka.mokatoyapp.MokaToyApplication
import com.moka.mokatoyapp.R
import kotlinx.android.synthetic.main.fragment_main.*
import rx.android.schedulers.AndroidSchedulers
import java.io.File

class MainFragment : BaseFragment() {

    private var currentBitmap: Bitmap? = null
    private lateinit var mImageView: MyImageViewDrawableOverlay

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater!!.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val overlay = LayoutInflater.from(activity).inflate(R.layout.view_drawable_overlay, null)
        mImageView = overlay.findViewById(R.id.drawable_overlay) as MyImageViewDrawableOverlay

        val params = ViewGroup.LayoutParams(ScreenUtil.getWidthPixels(activity), ScreenUtil.getWidthPixels(activity))
        mImageView.layoutParams = params
        overlay.layoutParams = params
        drawing_view_container.addView(overlay)

        gpuImageView.layoutParams = RelativeLayout.LayoutParams(ScreenUtil.getWidthPixels(activity), ScreenUtil.getWidthPixels(activity))

        workInBack(
                {
                    Glide.with(activity)
                            .load(R.drawable.bg_sample)
                            .asBitmap()
                            .into(-1, -1)
                            .get()
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { it ->
                    currentBitmap = it
                    gpuImageView.setImage(it)

                    val logoHeight = ScreenUtil.dipToPixel(activity, 36.0).toInt()
                    EffectUtil.addLogoSticker(
                            mImageView,
                            activity,
                            Bitmap.createScaledBitmap(BitmapFactory.decodeResource(activity.resources, R.drawable.ig_logo_share), (logoHeight * 3.1).toInt(), logoHeight, true),
                            "bottomCenter")

                    EffectUtil.addTimeSticker(
                            mImageView,
                            activity,
                            TextUtil.dateFormat("11 : 05 ", "AM", "\n  2016. 10. 14 (금)").asBitmap(
                                    ScreenUtil.dipToPixel(activity, 43.0).toFloat(),
                                    ContextCompat.getColor(MokaToyApplication.context, R.color.base_text_view_color), true))
                }

        imageView_a.setOnClickListener {
            workInBack(
                    {
                        Glide.with(activity)
                                .load(R.drawable.bg_sampe2)
                                .asBitmap()
                                .into(-1, -1)
                                .get()
                    })
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { it ->
                        gpuImageView.gpuImage.deleteImage()
                        currentBitmap = it
                        gpuImageView.setImage(it)
                    }
        }

        imageView_b.setOnClickListener {
            workInBack(
                    {
                        Glide.with(activity)
                                .load(R.drawable.bg_sample)
                                .asBitmap()
                                .into(-1, -1)
                                .get()
                    })
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { it ->
                        gpuImageView.gpuImage.deleteImage()
                        currentBitmap = it
                        gpuImageView.setImage(it)
                    }
        }

        imageView_c.setOnClickListener {
            EffectUtil.removeDateHv(mImageView)
            EffectUtil.addTimeSticker(
                    mImageView,
                    activity,
                    TextUtil.dateFormat("11 : 05 ", "AM", "\n  2016. 10. 14 (금)").asBitmap(
                            ScreenUtil.dipToPixel(activity, 43.0).toFloat(),
                            ContextCompat.getColor(MokaToyApplication.context, R.color.white), true))
        }

        imageView_d.setOnClickListener {
            EffectUtil.removeDateHv(mImageView)
            EffectUtil.addTimeSticker(
                    mImageView,
                    activity,
                    TextUtil.dateFormat("11 : 05 ", "AM", "\n  2016. 10. 14 (금)").asBitmap(
                            ScreenUtil.dipToPixel(activity, 43.0).toFloat(),
                            ContextCompat.getColor(MokaToyApplication.context, R.color.base_text_view_color), true))
        }

        imageView_e.setOnClickListener {
            savePicture()
        }
    }

    var type: String = "image/*"

    private fun savePicture() {
        val newBitmap = Bitmap.createBitmap(mImageView.width, mImageView.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(newBitmap)
        val dst = RectF(0f, 0f, mImageView.width.toFloat(), mImageView.height.toFloat())
        try {
            canvas.drawBitmap(gpuImageView.capture(), null, dst, null)
        } catch (e: InterruptedException) {
            e.printStackTrace()
            canvas.drawBitmap(currentBitmap, null, dst, null)
        }

        imageView_a.setImageBitmap(newBitmap)
        EffectUtil.applyOnSave(canvas, mImageView)
        SaveImageUtil.from(newBitmap, activity).setImageLocation(ImageLocation.EXTERNAL).start { imageNames ->
            MLog.deb("ddfalfaelfjlaklfjkleafjklefjalfjekl : fileName : ${imageNames[0]} ")
            createInstagramIntent(type, ImageFileUtil.from(activity).getExternalFilePath(imageNames[0]))
        }
    }

    private fun createInstagramIntent(type: String, mediaPath: String) {
        val share = Intent(Intent.ACTION_SEND)
        share.`package` = "com.instagram.android";
        share.type = type

        val media = File(mediaPath)
        val uri = Uri.fromFile(media)

        share.putExtra(Intent.EXTRA_STREAM, uri)
        startActivity(Intent.createChooser(share, "Share to"))
    }
}
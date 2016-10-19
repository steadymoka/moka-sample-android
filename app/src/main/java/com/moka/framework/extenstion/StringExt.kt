package com.moka.framework.extenstion

import android.graphics.*
import android.support.v4.content.ContextCompat
import android.text.*
import android.text.style.RelativeSizeSpan
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.moka.framework.util.ScreenUtil
import com.moka.mokatoyapp.MokaToyApplication
import com.moka.mokatoyapp.R
import com.moka.mokatoyapp.server.FailureResponse

fun String.parseFailure(): FailureResponse? {
    val result: FailureResponse?

    try {
        result = Gson().fromJson(this, FailureResponse::class.java)
    } catch(err: JsonSyntaxException) {
        result = null
    }
    return result
}

fun <T> String.parse(className: Class<T>): T? {
    val result: T?

    try {
        result = Gson().fromJson(this, className)
    } catch(err: JsonSyntaxException) {
        result = null
    }
    return result
}

fun CharSequence.asBitmap(textSize: Float, textColor: Int = 0xFF000000.toInt(), isDate: Boolean = false): Bitmap {
    val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG or Paint.LINEAR_TEXT_FLAG)
    textPaint.style = Paint.Style.FILL
    textPaint.color = textColor
    textPaint.textSize = textSize

    val mTextLayout: StaticLayout
    if (isDate)
        mTextLayout = StaticLayout(this,
                textPaint,
                (textPaint.measureText(this.toString()) + 0.5f + ScreenUtil.dipToPixel(MokaToyApplication.context, 12.0)).toInt(),
                Layout.Alignment.ALIGN_NORMAL,
                1.0f, 0.0f, false)
    else
        mTextLayout = StaticLayout(this, textPaint, (textPaint.measureText(this.toString()) + 0.5f).toInt(), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false)

    val bitmap = Bitmap.createBitmap(mTextLayout.width, mTextLayout.height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)

    canvas.save()
    canvas.translate(0F, 0F)
    mTextLayout.draw(canvas)
    canvas.restore()

    return bitmap
}

fun String.sizeof(size: Float): CharSequence {
    val spannableString = SpannableString(this)
    spannableString.setSpan(RelativeSizeSpan(size), 0, spannableString.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

    return spannableString
}
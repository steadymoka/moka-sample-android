package com.moka.framework.util


import android.content.Context
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import com.moka.mokatoyapp.MokaToyApplication

import com.moka.mokatoyapp.R


object TextUtil {

    fun afterSmall(first: String, second: String): CharSequence {
        val spannableStringBuilder = SpannableStringBuilder()

        spannableStringBuilder.append(first)

        val spannableString = SpannableString(second)
        spannableString.setSpan(RelativeSizeSpan(0.8f), 0, spannableString.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableStringBuilder.append(spannableString)

        return spannableStringBuilder
    }

    fun afterSmall(first: String, second: String, ratio: Float): CharSequence {
        val spannableStringBuilder = SpannableStringBuilder()

        spannableStringBuilder.append(first)

        val spannableString = SpannableString(second)
        spannableString.setSpan(RelativeSizeSpan(ratio), 0, spannableString.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableStringBuilder.append(spannableString)

        return spannableStringBuilder
    }

    fun sizeof(text: String, size: Float = 1f): CharSequence {
        val spannableString = SpannableString(text)
//        spannableString.setSpan(RelativeSizeSpan(size), 0, spannableString.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//        spannableString.setSpan(ForegroundColorSpan(ContextCompat.getColor(MokaToyApplication.context, R.color.white_pressed)), 0, spannableString.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        return spannableString
    }

    fun dateFormat(first: String, second: String, third: String): CharSequence {
        val spannableStringBuilder = SpannableStringBuilder()

        val aa = SpannableString(first)
        aa.setSpan(RelativeSizeSpan(3.6f), 0, aa.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//        aa.setSpan(ForegroundColorSpan(ContextCompat.getColor(MokaToyApplication.context, R.color.white)), 0, aa.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableStringBuilder.append(aa)

        val bb = SpannableString(second)
        bb.setSpan(RelativeSizeSpan(1f), 0, bb.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableStringBuilder.append(bb)

        val cc = SpannableString(third)
        cc.setSpan(RelativeSizeSpan(1.2f), 0, cc.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableStringBuilder.append(cc)

        return spannableStringBuilder
    }

}

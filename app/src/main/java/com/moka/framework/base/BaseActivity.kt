package com.moka.framework.base

import android.support.v7.app.AppCompatActivity
import com.moka.mokatoyapp.R

abstract class BaseActivity : AppCompatActivity() {

    private var isSlide: Boolean = false

    fun onSlideAnimationOnFinish() {
        isSlide = true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (isSlide)
            overridePendingTransition(R.anim.fade_in_short, R.anim.slide_out_right)
    }

}
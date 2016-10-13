package com.moka.mokatoyapp.vp


import android.os.Bundle
import com.moka.framework.base.BaseActivity
import com.moka.mokatoyapp.R


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout_container, MainFragment())
                .commit()
    }

}

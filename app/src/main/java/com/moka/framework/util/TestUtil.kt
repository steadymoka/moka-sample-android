package com.moka.framework.util


import com.moka.mokatoyapp.BuildConfig


object TestUtil {

    val isDebugMode = BuildConfig.DEBUG

    val isReleaseMode: Boolean
        get() = !isDebugMode

    val isDev: Boolean
        get() = BuildConfig.FLAVOR == "dev"

    val isProd: Boolean
        get() = BuildConfig.FLAVOR == "prod"

}

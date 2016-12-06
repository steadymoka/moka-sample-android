package com.moka.mokatoyapp


import android.text.TextUtils
import com.moka.framework.util.SharedPreferenceManager


class MokaPreference private constructor() : SharedPreferenceManager(MokaPreference.PREFERENCE_NAME) {

    /**
     * 앱이 설치후 한번만 실행 해야할 동작 설정
     */

    var isFirstRun: Boolean
        get() = getSharedPreferences(context).getBoolean(KEY_FIRST_RUN, true)
        set(firstRun) {
            getEditor(context).putBoolean(KEY_FIRST_RUN, firstRun).commit()
        }

    /**
     * 앱 버전 저장
     * 업데이트 됐을때 한번만 실행해야할 로직 설정 할때 필요
     */

    var lastAppVersion: String
        get() = getSharedPreferences(context).getString(KEY_LAST_APP_VERSION, "0.0.1")
        set(appVersion) {
            getEditor(context).putString(KEY_LAST_APP_VERSION, appVersion).commit()
        }

    /**
     * 로그인/회원가입 시 서버에서 받은 토큰(세션)값이 저장된다
     * 토큰이 저장되어 있으면 로그인이 된것이다
     */

    var authenticationToken: String
        get() = getSharedPreferences(context).getString(KEY_AUTHENTICATION_TOKEN, "")
        set(authenticationToken) {
            getEditor(context).putString(KEY_AUTHENTICATION_TOKEN, authenticationToken).commit()
        }

    /**
     */

    companion object {

        private val PREFERENCE_NAME = "moka_shared_preference"

        private var mInstance: MokaPreference? = null
        private val context = MokaToyApplication.context

        fun getInstance(): MokaPreference {
            if (null == mInstance)
                mInstance = MokaPreference()
            return mInstance!!
        }

        /**
         * KEYs
         */

        private val KEY_FIRST_RUN = "Moca.KEY_FIRST_RUN"
        private val KEY_LAST_APP_VERSION = "Moca.KEY_LAST_APP_VERSION"
        private val KEY_AUTHENTICATION_TOKEN = "Moca.KEY_AUTHENTICATION_TOKEN"

        /**
         */

        val isLoggedIn: Boolean
            get() = !TextUtils.isEmpty(getInstance().authenticationToken)

    }

}

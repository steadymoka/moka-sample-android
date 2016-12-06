package com.moka.framework.util


import android.content.Context
import android.content.SharedPreferences


open class SharedPreferenceManager(private val SHARED_PREFERENCE_NAME: String) {

    protected fun getEditor(context: Context): SharedPreferences.Editor {
        return getSharedPreferences(context).edit()
    }

    protected fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    fun clearPreference(context: Context) {
        getEditor(context).clear().commit()
    }

}

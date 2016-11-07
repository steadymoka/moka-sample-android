package com.moka.framework.util;


import android.content.Context;
import android.content.SharedPreferences;


public class SharedPreferenceManager {

	private final String SHARED_PREFERENCE_NAME;

	public SharedPreferenceManager(String sharedPreferenceName) {
		this.SHARED_PREFERENCE_NAME = sharedPreferenceName;
	}

	protected SharedPreferences.Editor getEditor(Context context) {
		return getSharedPreferences(context).edit();
	}

	protected SharedPreferences getSharedPreferences(Context context) {
		return context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
	}

	public void clearPreference(Context context) {
		getEditor(context).clear().commit();
	}

}

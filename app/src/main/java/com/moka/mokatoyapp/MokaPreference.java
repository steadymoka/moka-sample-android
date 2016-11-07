package com.moka.mokatoyapp;


import android.content.Context;
import android.text.TextUtils;

import com.moka.framework.util.SharedPreferenceManager;


public class MokaPreference extends SharedPreferenceManager {

	private static final String PREFERENCE_NAME = "moka_shared_preference";

	private static MokaPreference instance;
	private static Context context = MokaToyApplication.Companion.getContext();

	private MokaPreference() {
		super(PREFERENCE_NAME);
	}

	/**
	 * 앱이 설치후 한번만 실행 해야할 동작 설정
	 */

	private static final String KEY_FIRST_RUN = "Moca.KEY_FIRST_RUN";

	public void setFirstRun(boolean firstRun) {
		getEditor(context).putBoolean(KEY_FIRST_RUN, firstRun).commit();
	}

	public boolean isFirstRun() {
		return getSharedPreferences(context).getBoolean(KEY_FIRST_RUN, true);
	}

	/**
	 * 앱 버전 저장
	 * 업데이트 됐을때 한번만 실행해야할 로직 설정 할때 필요
	 */

	private static final String KEY_LAST_APP_VERSION = "Moca.KEY_LAST_APP_VERSION";

	public void setLastAppVersion(String appVersion) {
		getEditor(context).putString(KEY_LAST_APP_VERSION, appVersion).commit();
	}

	public String getLastAppVersion() {
		return getSharedPreferences(context).getString(KEY_LAST_APP_VERSION, "0.0.1");
	}

	/**
	 * 로그인/회원가입 시 서버에서 받은 토큰(세션)값이 저장된다
	 * 토큰이 저장되어 있으면 로그인이 된것이다
	 */

	private static final String KEY_AUTHENTICATION_TOKEN = "Moca.KEY_AUTHENTICATION_TOKEN";

	public void setAuthenticationToken(String authenticationToken) {
		getEditor(context).putString(KEY_AUTHENTICATION_TOKEN, authenticationToken).commit();
	}

	public String getAuthenticationToken() {
		return getSharedPreferences(context).getString(KEY_AUTHENTICATION_TOKEN, "");
	}

	public static boolean isLoggedIn() {
		return !TextUtils.isEmpty(getInstance().getAuthenticationToken());
	}

	/**
	 * SET / GET instance & context *****************************
	 */

	public static MokaPreference getInstance() {
		if ( null == instance )
			instance = new MokaPreference();

		return instance;
	}

}

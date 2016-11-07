package com.moka.mokatoyapp._di.module

import android.content.Context
import com.moka.framework.util.TestUtil
import com.moka.mokatoyapp.BuildConfig
import com.moka.mokatoyapp.MokaPreference
import com.moka.mokatoyapp.server.ServerInfo
import com.moka.mokatoyapp.server.api.MokaAPI
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule(val context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context = context

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        if (TestUtil.isDebugMode)
            return OkHttpClient.Builder()
                    .addInterceptor(initHttpLoggingInterceptor())
                    .addInterceptor(UrlInterceptor())
                    .build()
        else
            return OkHttpClient.Builder()
                    .addInterceptor(initHttpLoggingInterceptor())
                    .addInterceptor(UrlInterceptor())
                    .build()
    }

    private fun initHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            }
            else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    class UrlInterceptor : Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response? {
            val r = chain.request()
            val url = r.url().newBuilder()
                    .addQueryParameter("app", "android")
                    .build()
            val request = r.newBuilder()
                    .url(url)
                    .addHeader("Accept", "application/vnd.moca.v1+json")
                    .addHeader("Cookie", MokaPreference.getInstance().authenticationToken)
                    .build()

            return chain.proceed(request)
        }
    }

    /**
     */

    @Provides
    @Singleton
    fun provideMocaApiService(client: OkHttpClient): MokaAPI.API {
        val endpoint = ServerInfo.END_POINT_API_SERVER

        val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(endpoint)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit.create(MokaAPI.API::class.java)
    }

}

package com.sundaydev.kakaoTest.network

import android.content.Context
import com.sundaydev.kakaoTest.R
import okhttp3.Interceptor
import okhttp3.Response
import org.koin.core.KoinComponent
import org.koin.core.inject

class CommonParam : Interceptor, KoinComponent {
    private val context: Context by inject()

    override fun intercept(chain: Interceptor.Chain): Response =
        chain.proceed(chain.request().apply {
            url().newBuilder()
                .addQueryParameter(PARAM_API_KEY, context.getString(R.string.api_key))
                .addQueryParameter(PARAM_LANGUAGE, "ko").build()
        })
}
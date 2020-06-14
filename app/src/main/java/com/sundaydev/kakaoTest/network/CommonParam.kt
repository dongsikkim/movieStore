package com.sundaydev.kakaoTest.network

import android.content.Context
import com.sundaydev.kakaoTest.R
import okhttp3.Interceptor
import okhttp3.Response
import org.koin.core.KoinComponent
import org.koin.core.inject

const val PARAM_API_KEY = "api_key"
const val PARAM_LANGUAGE = "language"

class CommonParam : Interceptor, KoinComponent {
    private val context: Context by inject()
    override fun intercept(chain: Interceptor.Chain): Response {
        val httpUrl = chain.request().url().newBuilder()
            .addQueryParameter(PARAM_API_KEY, context.getString(R.string.api_key))
            .addQueryParameter(PARAM_LANGUAGE, "ko").build()
        return chain.proceed(chain.request().newBuilder().url(httpUrl).build())
    }
}
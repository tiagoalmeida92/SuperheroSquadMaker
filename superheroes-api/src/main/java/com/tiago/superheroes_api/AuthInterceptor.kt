package com.tiago.superheroes_api

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val configProvider: ApiConfigProvider
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url()

        val url = originalUrl.newBuilder()
            .addQueryParameter("apikey", configProvider.getApiKey())
            .build()

        val requestBuilder = originalRequest.newBuilder()
            .addHeader("Referer", configProvider.getReferer())
            .url(url)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}

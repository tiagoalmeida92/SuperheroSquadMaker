package com.tiago.superheroes_api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CharactersApiFactoryImpl(
    private val apiConfigProvider: ApiConfigProvider
) : CharactersApiFactory {

    override fun createForEndpoint(baseUrl: String): SuperheroesApi {
        val client = OkHttpClient()
            .newBuilder()
            .addInterceptor(AuthInterceptor(apiConfigProvider))
            .build()
        return Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SuperheroesApi::class.java)
    }
}

package com.tiago.superherosquadmaker.di.network

import com.google.gson.Gson
import com.tiago.superheroes_api.ApiConfigProvider
import com.tiago.superheroes_api.AuthInterceptor
import com.tiago.superherosquadmaker.config.ApiConfigProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideHttpClient(
        apiConfigProvider: ApiConfigProvider
    ): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(AuthInterceptor(apiConfigProvider))
            .build()
    }

    @Provides
    fun providesApiConfigProvider(): ApiConfigProvider {
        return ApiConfigProviderImpl()
    }
}
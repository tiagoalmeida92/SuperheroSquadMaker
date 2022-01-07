package com.tiago.superherosquadmaker.di.modules

import com.google.gson.Gson
import com.tiago.superheroes_api.ApiConfigProvider
import com.tiago.superheroes_api.AuthInterceptor
import com.tiago.superherosquadmaker.di.network.NetworkModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class]
)
class MockServerNetworkModule {

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
        return object :ApiConfigProvider{
            override fun getBaseUrl(): String {
                return "http://localhost:8080"
            }

            override fun getApiKey(): String {
                return ""
            }

            override fun getReferer(): String {
                return ""
            }

        }
    }


}
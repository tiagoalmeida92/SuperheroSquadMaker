package com.tiago.superherosquadmaker.di.features.superherolist

import com.tiago.superheroes_api.ApiConfigProvider
import com.tiago.superheroes_api.SuperheroService
import com.tiago.superheroes_api.SuperheroesApi
import com.tiago.superheroes_api.utils.MarvelServiceMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class SuperheroesServiceModule {

    @Provides
    fun providesSuperheroesService(
        superheroesApi: SuperheroesApi
    ): SuperheroService {
        return SuperheroService(superheroesApi, MarvelServiceMapperImpl())
    }

    @Provides
    fun providesSuperheroesApi(
        okHttpClient: OkHttpClient,
        configProvider: ApiConfigProvider
    ): SuperheroesApi {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(configProvider.getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SuperheroesApi::class.java)
    }

}
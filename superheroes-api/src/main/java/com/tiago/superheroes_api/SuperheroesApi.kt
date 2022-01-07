package com.tiago.superheroes_api

import com.tiago.superheroes_api.responses.MsvCharactersResponse
import retrofit2.http.GET

interface SuperheroesApi {
    @GET("characters")
    suspend fun characters(): MsvCharactersResponse
}

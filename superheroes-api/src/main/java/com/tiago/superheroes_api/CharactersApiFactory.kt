package com.tiago.superheroes_api

interface CharactersApiFactory {
    fun createForEndpoint(baseUrl: String): SuperheroesApi
}

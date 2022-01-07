package com.tiago.superheroes_api

import com.tiago.superheroes_api.utils.MarvelServiceMapper
import com.tiago.superheroes_domain.Superhero

class SuperheroService(
    private val superheroesApi: SuperheroesApi,
    private val mapper: MarvelServiceMapper
) {

    suspend fun getSuperheroes(): List<Superhero> {
        return superheroesApi.characters().data.results.map {
            mapper.mapToSuperhero(it)
        }
    }
}

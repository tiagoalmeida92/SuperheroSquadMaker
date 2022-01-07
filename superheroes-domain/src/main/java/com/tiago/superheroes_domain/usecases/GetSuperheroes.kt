package com.tiago.superheroes_domain.usecases

import com.tiago.superheroes_domain.repository.SuperheroRepository

class GetSuperheroes(
    private val superheroRepository: SuperheroRepository
) {

    suspend fun get() = superheroRepository.getAll()

}
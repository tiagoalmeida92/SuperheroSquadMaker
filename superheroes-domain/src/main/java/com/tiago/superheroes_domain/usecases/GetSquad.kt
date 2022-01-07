package com.tiago.superheroes_domain.usecases

import com.tiago.superheroes_domain.repository.SuperheroRepository
import kotlinx.coroutines.flow.map

class GetSquad(
    private val superheroRepository: SuperheroRepository
) {

    suspend fun get() = superheroRepository.getInSquad()

}
package com.tiago.superheroes_domain.usecases

import com.tiago.superheroes_domain.Superhero
import com.tiago.superheroes_domain.repository.SuperheroRepository

class FireSuperhero(
    private val superheroRepository: SuperheroRepository
) {
    suspend fun fire(superhero: Superhero) {
        superheroRepository.update(
            superhero.copy(isInSquad = false)
        )
    }
}
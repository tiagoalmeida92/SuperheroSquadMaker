package com.tiago.superheroes_domain.usecases

import com.tiago.superheroes_domain.Superhero
import com.tiago.superheroes_domain.repository.SuperheroRepository

class HireSuperhero(
    private val superheroRepository: SuperheroRepository
) {
    suspend fun hire(superhero: Superhero) {
        superheroRepository.update(
            superhero.copy(isInSquad = true)
        )
    }
}
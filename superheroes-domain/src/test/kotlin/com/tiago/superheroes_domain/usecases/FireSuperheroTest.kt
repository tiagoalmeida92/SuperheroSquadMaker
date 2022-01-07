package com.tiago.superheroes_domain.usecases

import com.tiago.superheroes_domain.Superhero
import com.tiago.superheroes_domain.repository.SuperheroRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class FireSuperheroTest {

    private val superheroRepository: SuperheroRepository = mock()

    val useCase = FireSuperhero(superheroRepository)

    @Test
    fun `WHEN usecase action THEN repository is called with isInSquad false`(){
        runBlocking {
            val superhero = Superhero(1, "Hulk", "", "", true)
            val superheroNotInSquad = superhero.copy(isInSquad = false)

            useCase.fire(superhero)

            verify(superheroRepository).update(superheroNotInSquad)
        }
    }

}
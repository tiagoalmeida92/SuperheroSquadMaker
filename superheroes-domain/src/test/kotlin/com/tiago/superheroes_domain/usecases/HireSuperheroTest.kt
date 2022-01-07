package com.tiago.superheroes_domain.usecases

import com.tiago.superheroes_domain.Superhero
import com.tiago.superheroes_domain.repository.SuperheroRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class HireSuperheroTest {

    private val superheroRepository: SuperheroRepository = mock()

    val useCase = HireSuperhero(superheroRepository)

    @Test
    fun `WHEN usecase action THEN repository is called with isInSquad true`(){
        runBlocking {
            val superhero = Superhero(1, "Hulk", "", "", false)
            val superheroInSquad = superhero.copy(isInSquad = true)

            useCase.hire(superhero)

            verify(superheroRepository).update(superheroInSquad)
        }
    }

}
package com.tiago.superheroes_domain.usecases

import com.tiago.superheroes_domain.Superhero
import com.tiago.superheroes_domain.repository.SuperheroRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class GetSuperheroesTest {

    private val superheroRepository: SuperheroRepository = mock()

    val usecase = GetSuperheroes(superheroRepository)

    @Test
    fun `WHEN getSuperheroes THEN repository gets all`(){
        runBlocking {
            val testSquad = testSuperheroList()
            given(superheroRepository.getAll())
                .willReturn(
                    flow {
                        emit(testSquad)
                    }
                )

            val squad = usecase.get().first()

            assertThat(squad).isEqualTo(testSquad)
            verify(superheroRepository).getAll()
        }

    }

    private fun testSuperheroList() = listOf(
        Superhero(1, "Hulk", "", "", true),
        Superhero(3, "Thor", "", "", false),
        Superhero(5, "Ironman", "", "", false)
    )

}
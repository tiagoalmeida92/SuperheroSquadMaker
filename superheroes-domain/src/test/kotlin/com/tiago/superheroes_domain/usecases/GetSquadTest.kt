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

class GetSquadTest {

    private val superheroRepository: SuperheroRepository = mock()

    val usecase = GetSquad(superheroRepository)

    @Test
    fun `WHEN getSquad THEN repository calls getInSquad`(){
        runBlocking {
            val testSquad = testSuperheroList()
            given(superheroRepository.getInSquad())
                .willReturn(
                    flow {
                        emit(testSquad)
                    }
                )

            val squad = usecase.get().first()

            assertThat(squad).isEqualTo(testSquad)
            verify(superheroRepository).getInSquad()
        }

    }

    private fun testSuperheroList() = listOf(
        Superhero(1, "Hulk", "", "", true),
        Superhero(3, "Thor", "", "", true),
        Superhero(5, "Ironman", "", "", true)
    )

}
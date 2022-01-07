package com.tiago.superheroes_data

import com.tiago.superheroes_api.SuperheroService
import com.tiago.superheroes_database.SuperheroDatabase
import com.tiago.superheroes_domain.Superhero
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*
import java.lang.Exception

@ExperimentalCoroutinesApi
class SuperheroRepositoryImplTest {

    private val database: SuperheroDatabase = mock()
    private val service: SuperheroService = mock()

    private val testDispatcher = TestCoroutineDispatcher()
    private val repository = SuperheroRepositoryImpl(database, service)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `WHEN repository getAll and service fetches successfully THEN insert into database and result returned `(){
        runBlockingTest(testDispatcher) {
            val superheroes = testSuperheroList()
            given(database.getAll())
                .willReturn(flow {
                    emit(emptyList())
                    emit(superheroes)
                })
            given(service.getSuperheroes())
                .willReturn(superheroes)

            val resultFlow = repository.getAll()

            val result = resultFlow.take(2).toList()

            assertThat(result[0]).isEqualTo(emptyList<Superhero>())
            assertThat(result[1]).isEqualTo(superheroes)

            verify(database).getAll()
            verify(database).insertAll(any())
            verify(service).getSuperheroes()
        }
    }

    @Test
    fun `WHEN repository getAll and service throws exception THEN database result returned`() {
        runBlockingTest(testDispatcher) {
            val superheroes = testSuperheroList()
            given(database.getAll())
                .willReturn(flow {
                    emit(superheroes)
                })
            given(service.getSuperheroes())
                .willAnswer {
                    throw Exception()
                }

            val result = repository.getAll().first()

            assertThat(result).isEqualTo(superheroes)
            verify(database).getAll()
            verify(database, times(0)).insertAll(any())
            verify(service).getSuperheroes()
        }
    }

    @Test
    fun `WHEN repository getAll and service throws exception THEN retries and updates database`() {
        runBlockingTest(testDispatcher) {
        val superheroes = testSuperheroList()
            given(database.getAll())
                .willReturn(flow {
                    emit(emptyList())
                    emit(superheroes)
                })
            given(service.getSuperheroes())
                .willAnswer {
                    throw Exception()
                }.willAnswer {
                    superheroes
                }

            val resultFlow = repository.getAll()
            val result = resultFlow.take(3).toList()

            assertThat(result[0]).isEqualTo(emptyList<Superhero>())
            assertThat(result[1]).isEqualTo(superheroes)
            assertThat(result[2]).isEqualTo(superheroes)

            verify(database).getAll()
            verify(database).insertAll(any())
            verify(service, times(2)).getSuperheroes()
        }
    }

    @Test
    fun `WHEN repository getAll and service fetches updated data THEN insert into database and result returned `(){
        runBlockingTest(testDispatcher) {
            val superheroes = testSuperheroList()
            val updatedSuperheroes = testSuperheroUpdatedList()
            given(database.getAll())
                .willReturn(flow {
                    emit(emptyList())
                    emit(superheroes)
                    emit(updatedSuperheroes)
                })
            given(service.getSuperheroes())
                .willReturn(superheroes)
                .willReturn(updatedSuperheroes)

            val resultFlow = repository.getAll()

            val result = resultFlow.take(4).toList()

            assertThat(result[0]).isEqualTo(emptyList<Superhero>())
            assertThat(result[1]).isEqualTo(superheroes)
            assertThat(result[2]).isEqualTo(updatedSuperheroes)
            assertThat(result[3]).isEqualTo(updatedSuperheroes)

            verify(database).getAll()
            verify(database).insertAll(superheroes)
            verify(database).insertAll(updatedSuperheroes)
            verify(service, times(2)).getSuperheroes()
        }
    }

    private fun testSuperheroList() = listOf(
        Superhero(1, "Hulk", "", "", true),
        Superhero(3, "Thor", "", "", false),
        Superhero(5, "Ironman", "", "", false)
    )

    private fun testSuperheroUpdatedList() = listOf(
        Superhero(1, "Hulk", "", "", true),
        Superhero(3, "Thor", "", "", false),
        Superhero(5, "Ironman", "", "", false),
        Superhero(6, "Spiderman", "", "", false)
    )

}
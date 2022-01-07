package com.tiago.superheroes_ui.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.tiago.superheroes_domain.Superhero
import com.tiago.superheroes_domain.usecases.GetSquad
import com.tiago.superheroes_domain.usecases.GetSuperheroes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
class SuperheroListViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    private val getSuperheroes: GetSuperheroes = mock()
    private val getSquad: GetSquad = mock()

    lateinit var viewModel: SuperheroListViewModel

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
    fun `WHEN init THEN call getSuperheroes and getSquad and sort results`() {
        runBlockingTest(testDispatcher) {

            val superheroes = unsortedSuperheroList()
            val squad = unsortedTestSquad()

            given(getSuperheroes.get()).willReturn(
                flow { emit(superheroes) }
            )
            given(getSquad.get()).willReturn(
                flow { emit(squad) }
            )

            viewModel = SuperheroListViewModel(getSuperheroes, getSquad, testDispatcher)

            verify(getSuperheroes).get()
            verify(getSquad).get()

            viewModel.uiState.test {
                assertThat(awaitItem())
                    .isEqualTo(SuperheroListState.Data(sortedSquad(), sortedSuperheroList()))
            }
        }
    }

    private fun sortedSuperheroList() = listOf(
        Superhero(1, "Hulk", "", "", true),
        Superhero(3, "Ironman", "", "", true),
        Superhero(5, "Thor", "", "", false)
    )

    private fun unsortedSuperheroList() = listOf(
        Superhero(3, "Ironman", "", "", true),
        Superhero(1, "Hulk", "", "", true),
        Superhero(5, "Thor", "", "", false)
    )

    private fun sortedSquad() = listOf(
        Superhero(1, "Hulk", "", "", true),
        Superhero(3, "Ironman", "", "", true)
    )

    private fun unsortedTestSquad() = listOf(
        Superhero(3, "Ironman", "", "", true),
        Superhero(1, "Hulk", "", "", true)
    )
}
package com.tiago.superheroes_ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.tiago.superheroes_domain.Superhero
import com.tiago.superheroes_domain.usecases.FireSuperhero
import com.tiago.superheroes_domain.usecases.HireSuperhero
import com.tiago.superheroes_ui.detail.SuperheroDetailActivity.Companion.EXTRA_SUPERHERO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
class SuperheroDetailViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    private val hireSuperhero: HireSuperhero = mock()
    private val fireSuperhero: FireSuperhero = mock()

    lateinit var viewModel: SuperheroDetailViewModel

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
    fun `GIVEN superhero not in squad THEN on action hireSuperhero usecase called`(){
        runBlockingTest {
            val superhero = Superhero(1, "Hulk", "", "", false)
            val args = mapOf(EXTRA_SUPERHERO to superhero)
            val savedStateHandle = SavedStateHandle(args)

            viewModel = SuperheroDetailViewModel(savedStateHandle, hireSuperhero, fireSuperhero)

            assertThat(viewModel.uiState.value).isEqualTo(superhero)

            viewModel.onSquadButtonClick()
            verify(hireSuperhero).hire(superhero)

            assertThat(viewModel.uiState.value).isEqualTo(superhero.copy(isInSquad = true))

        }
    }

    @Test
    fun `GIVEN superhero is in squad THEN on action fireSuperhero usecase called`(){
        runBlockingTest {
            val superhero = Superhero(1, "Hulk", "", "", true)
            val args = mapOf(EXTRA_SUPERHERO to superhero)
            val savedStateHandle = SavedStateHandle(args)

            viewModel = SuperheroDetailViewModel(savedStateHandle, hireSuperhero, fireSuperhero)

            assertThat(viewModel.uiState.value).isEqualTo(superhero)

            viewModel.onSquadButtonClick()
            verify(fireSuperhero).fire(superhero)

            assertThat(viewModel.uiState.value).isEqualTo(superhero.copy(isInSquad = false))
        }
    }

}
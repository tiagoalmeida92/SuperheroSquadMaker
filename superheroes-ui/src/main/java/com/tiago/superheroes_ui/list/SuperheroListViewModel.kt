package com.tiago.superheroes_ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tiago.superheroes_domain.Superhero
import com.tiago.superheroes_domain.usecases.GetSquad
import com.tiago.superheroes_domain.usecases.GetSuperheroes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SuperheroListViewModel @Inject constructor(
    private val getListUseCase: GetSuperheroes,
    private val getSquadUseCase: GetSquad,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow<SuperheroListState>(SuperheroListState.Loading)
    val uiState: StateFlow<SuperheroListState> = _uiState

    init {
        viewModelScope.launch {
            withContext(dispatcher) {
                getListUseCase.get()
                    .combine(getSquadUseCase.get())
                    { list: List<Superhero>, squad: List<Superhero> ->
                        SuperheroListState.Data(
                            squad.sortedBy { hero -> hero.name },
                            list.sortedBy { hero -> hero.name }
                        )
                    }
                    .collect {
                        _uiState.value = it
                    }
            }
        }
    }

}

sealed class SuperheroListState {
    object Loading : SuperheroListState()
    data class Data(
        val squad: List<Superhero>,
        val superheroes: List<Superhero>
    ) : SuperheroListState()
}

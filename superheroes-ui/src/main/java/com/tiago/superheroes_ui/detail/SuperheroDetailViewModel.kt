package com.tiago.superheroes_ui.detail

import androidx.lifecycle.*
import com.tiago.superheroes_domain.Superhero
import com.tiago.superheroes_domain.usecases.FireSuperhero
import com.tiago.superheroes_domain.usecases.HireSuperhero
import com.tiago.superheroes_ui.detail.SuperheroDetailActivity.Companion.EXTRA_SUPERHERO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SuperheroDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val hireSuperhero: HireSuperhero,
    private val fireSuperhero: FireSuperhero
): ViewModel() {

    var superhero = savedStateHandle.get<Superhero>(EXTRA_SUPERHERO)!!

    private val _uiState = MutableLiveData(superhero)
    val uiState: LiveData<Superhero> = _uiState

    fun onSquadButtonClick() {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                if(superhero.isInSquad){
                    fireSuperhero.fire(superhero)
                } else {
                    hireSuperhero.hire(superhero)
                }
                superhero = superhero.copy(isInSquad = !superhero.isInSquad)
                _uiState.postValue(superhero)
            }
        }
    }

}

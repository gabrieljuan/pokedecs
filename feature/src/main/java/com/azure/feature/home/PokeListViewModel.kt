package com.azure.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azure.domain.model.Poke
import com.azure.domain.usecase.GetPokeListUseCase
import com.azure.domain.util.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokeListViewModel @Inject constructor(
    private val getPokeListUseCase: GetPokeListUseCase
) : ViewModel() {

    private val pokeList = mutableListOf<Poke>()
    private val _uiState = MutableStateFlow(PokeListViewState())
    val uiState: StateFlow<PokeListViewState> = _uiState

    fun getPokeList() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            when (val result = getPokeListUseCase(pokeList.size)) {
                is DataResult.Success -> {
                    result.value.forEach { poke ->
                        if (!pokeList.contains(poke)) {
                            pokeList.add(poke)
                        }
                    }
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            pokeList = pokeList,
                            isNetworkError = false,
                        )
                    }
                }

                is DataResult.Exception -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isNetworkError = true
                        )
                    }
                }
            }
        }
    }
}
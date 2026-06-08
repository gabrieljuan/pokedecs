package com.azure.feature.home.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azure.domain.model.Poke
import com.azure.domain.usecase.GetPokeDetailUseCase
import com.azure.domain.usecase.GetPokeListUseCase
import com.azure.domain.util.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokeListViewModel @Inject constructor(
    private val getPokeListUseCase: GetPokeListUseCase,
    private val getPokeDetailUseCase: GetPokeDetailUseCase,
) : ViewModel() {
    private val query = MutableStateFlow("")
    private val pokeList = mutableListOf<Poke>()
    private val searchList = mutableListOf<Poke>()
    private val _uiState = MutableStateFlow(PokeListViewState())
    val uiState: StateFlow<PokeListViewState> = _uiState

    init {
        observeSearchQuery()
        getPokeList()
    }

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

    fun searchPokeList(name: String) {
        viewModelScope.launch {
            when (val result = getPokeDetailUseCase(name)) {
                is DataResult.Success -> {
                    searchList.clear()
                    searchList.add(Poke(result.value.name))
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isNetworkError = false,
                            pokeList = searchList,
                        )
                    }
                }

                is DataResult.Exception -> {
                    _uiState.update {
                        it.copy(
                            isNetworkError = true,
                            isLoading = false,
                        )
                    }
                }
            }
        }
    }

    fun onQueryChanged(query: String) {
        this.query.value = query
        _uiState.update {
            it.copy(query = query)
        }
    }

    private fun observeSearchQuery() {
        query
            .debounce(300)
            .distinctUntilChanged()
            .onEach { searchQuery ->
                if (searchQuery.isBlank()) {
                    onClearQuery()
                } else {
                    searchPokeList(searchQuery)
                }
            }.launchIn(viewModelScope)
    }

    private fun onClearQuery() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(pokeList = pokeList)
            }
        }
    }
}
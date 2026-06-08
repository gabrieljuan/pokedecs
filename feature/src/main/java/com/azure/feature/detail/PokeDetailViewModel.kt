package com.azure.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azure.domain.usecase.GetPokeDetailUseCase
import com.azure.domain.util.DEFAULT_ERROR
import com.azure.domain.util.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokeDetailViewModel @Inject constructor(
    private val getPokeDetailUseCase: GetPokeDetailUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PokeDetailViewState())
    val uiState: StateFlow<PokeDetailViewState> = _uiState

    fun getPokeDetail(pokeName: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            when (val result = getPokeDetailUseCase(pokeName)) {
                is DataResult.Success -> {
                    _uiState.update {
                        it.copy(
                            pokeDetail = result.value,
                            isLoading = false,
                            errorMessage = null,
                        )
                    }
                }
                is DataResult.Exception -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = result.throwable.message ?: DEFAULT_ERROR,
                        )
                    }
                }
            }
        }
    }

    fun onErrorShown() {
        _uiState.update {
            it.copy(errorMessage = null)
        }
    }
}
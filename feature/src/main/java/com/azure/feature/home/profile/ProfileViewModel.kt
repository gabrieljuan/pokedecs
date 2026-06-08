package com.azure.feature.home.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azure.domain.usecase.GetProfileUseCase
import com.azure.domain.util.DEFAULT_ERROR
import com.azure.domain.util.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileViewState())
    val uiState: StateFlow<ProfileViewState> = _uiState

    fun getProfile(username: String) {
        viewModelScope.launch {
            when (val result = getProfileUseCase(username)) {
                is DataResult.Success -> {
                    _uiState.update {
                        it.copy(
                            user = result.value,
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
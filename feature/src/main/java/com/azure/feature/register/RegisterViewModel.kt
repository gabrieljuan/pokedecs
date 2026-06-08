package com.azure.feature.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azure.domain.util.DEFAULT_ERROR
import com.azure.domain.usecase.RegisterUseCase
import com.azure.domain.util.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterViewState())
    val uiState: StateFlow<RegisterViewState> = _uiState

    fun register(
        username: String,
        password: String,
        email: String,
        phone: String,
        about: String
    ) {
        viewModelScope.launch {
            when (val result = registerUseCase(
                username = username,
                password = password,
                phone = phone,
                email = email,
                about = about
            )) {
                is DataResult.Success -> {
                    _uiState.update {
                        it.copy(
                            isRegisterSuccess = true,
                            username = result.value.username,
                            isLoading = false,
                            errorMessage = ""
                        )
                    }
                }
                is DataResult.Exception -> {
                    _uiState.update {
                        it.copy(
                            isRegisterSuccess = false,
                            username = "",
                            isLoading = false,
                            errorMessage = result.throwable.message ?: DEFAULT_ERROR
                        )
                    }
                }
            }
        }
    }
}
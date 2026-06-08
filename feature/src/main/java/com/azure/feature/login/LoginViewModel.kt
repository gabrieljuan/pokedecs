package com.azure.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azure.domain.util.DEFAULT_ERROR
import com.azure.domain.usecase.LoginUseCase
import com.azure.domain.util.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(LoginViewState())
    val uiState: StateFlow<LoginViewState> = _uiState

    fun login(username: String, password: String) {
        viewModelScope.launch {
            when(val result = loginUseCase(username = username, password = password)){
                is DataResult.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isLoginSuccess = true,
                            username = result.value.username,
                            errorMessage = ""
                        )
                    }
                }
                is DataResult.Exception -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isLoginSuccess = false,
                            errorMessage = result.throwable.message ?: DEFAULT_ERROR
                        )
                    }
                }
            }
        }
    }
}
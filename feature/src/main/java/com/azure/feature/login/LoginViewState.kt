package com.azure.feature.login

data class LoginViewState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isLoginSuccess: Boolean = false,
    val username: String = ""
)

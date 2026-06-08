package com.azure.feature.register

data class RegisterViewState(
    val isRegisterSuccess: Boolean = false,
    val username: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String = "",
)

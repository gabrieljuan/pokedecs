package com.azure.feature.home.profile

import com.azure.domain.model.User

data class ProfileViewState(
    val user: User = User(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
)

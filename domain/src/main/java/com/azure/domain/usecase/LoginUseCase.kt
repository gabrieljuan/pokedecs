package com.azure.domain.usecase

import com.azure.domain.repository.UserRepository

class LoginUseCase (
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(username: String, password:String) =
        userRepository.login(username, password)
}
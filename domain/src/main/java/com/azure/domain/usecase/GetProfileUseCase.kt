package com.azure.domain.usecase

import com.azure.domain.repository.UserRepository

class GetProfileUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(username: String) =
        userRepository.getProfile(username)
}
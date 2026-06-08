package com.azure.domain.usecase

import com.azure.domain.model.User
import com.azure.domain.repository.UserRepository
import com.azure.domain.util.DataResult

class RegisterUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(
        username: String,
        password: String,
        phone: String,
        email: String,
        about: String,
    ): DataResult<User> {
        val user = userRepository.register(username, password)
        userRepository.saveProfile(
            User(
                username = username,
                phone = phone,
                email = email,
                about = about,
            )
        )
        return user
    }
}
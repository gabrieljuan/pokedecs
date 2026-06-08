package com.azure.domain.repository

import com.azure.domain.model.User
import com.azure.domain.util.DataResult

interface UserRepository {

    suspend fun saveProfile(user: User): DataResult<Unit>

    suspend fun getProfile(username: String): DataResult<User>

    suspend fun login(username: String, password: String): DataResult<User>

    suspend fun register(username: String, password: String): DataResult<User>
}
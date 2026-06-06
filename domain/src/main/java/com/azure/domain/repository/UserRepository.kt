package com.azure.domain.repository

import com.azure.domain.model.User
import com.azure.domain.util.DataResult

interface UserRepository {

    suspend fun saveProfile(user: User): DataResult<Nothing>

    suspend fun getProfile(username: String): DataResult<User>

    suspend fun login(username: String, password: String): DataResult<User>

    suspend fun signup(username: String, password: String): DataResult<User>
}
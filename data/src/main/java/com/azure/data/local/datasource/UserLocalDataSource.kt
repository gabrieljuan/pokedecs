package com.azure.data.local.datasource

import com.azure.domain.model.User
import com.azure.domain.util.DataResult

interface UserLocalDataSource {
    suspend fun login(username: String, password: String): DataResult<User>
    suspend fun register(username: String, password: String): DataResult<User>

    suspend fun saveProfile(username: String, email: String, phone: String, about: String): DataResult<Unit>

    suspend fun getProfile(username: String): DataResult<User>
}
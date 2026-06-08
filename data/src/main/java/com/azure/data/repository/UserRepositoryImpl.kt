package com.azure.data.repository

import com.azure.data.local.datasource.UserLocalDataSource
import com.azure.domain.model.User
import com.azure.domain.repository.UserRepository
import com.azure.domain.util.DataResult
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource
) : UserRepository {
    override suspend fun saveProfile(user: User): DataResult<Unit> =
        userLocalDataSource.saveProfile(user.username, user.email, user.phone, user.about)

    override suspend fun getProfile(username: String): DataResult<User> =
        userLocalDataSource.getProfile(username)

    override suspend fun login(username: String, password: String): DataResult<User> =
        userLocalDataSource.login(username, password)

    override suspend fun register(username: String, password: String): DataResult<User> =
        userLocalDataSource.register(username, password)

}
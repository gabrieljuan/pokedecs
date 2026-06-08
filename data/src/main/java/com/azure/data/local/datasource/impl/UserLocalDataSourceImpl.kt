package com.azure.data.local.datasource.impl

import com.azure.data.di.qualifier.UserCollection
import com.azure.data.local.datasource.UserLocalDataSource
import com.azure.data.remote.mapper.toUserDomain
import com.azure.domain.model.User
import com.azure.domain.util.DataResult
import com.azure.domain.util.tryGetDataCall
import com.couchbase.lite.Collection
import com.couchbase.lite.MutableDocument
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(
    @UserCollection private val userCollection: Collection
) : UserLocalDataSource {
    override suspend fun login(username: String, password: String): DataResult<User> = tryGetDataCall {
        val user = userCollection.getDocument("user-$username")
        if (user?.getString("password") == password) {
            user.toUserDomain()
        } else {
            throw Throwable("Please check your username and password")
        }
    }

    override suspend fun register(
        username: String,
        password: String,
    ): DataResult<User> = tryGetDataCall {
        val userDocument = MutableDocument("user-$username").apply {
            setString("type", "user")
            setString("username", username)
            setString("password", password)
        }
        userCollection.save(userDocument)
        userDocument.toUserDomain()
    }

    override suspend fun saveProfile(username: String, email: String, phone: String, about: String): DataResult<Unit> = tryGetDataCall {
        val profileDocument = MutableDocument("profile-$username").apply {
            setString("type", "user")
            setString("username", username)
            setString("email", email)
            setString("phone", phone)
            setString("about", about)
        }
        userCollection.save(profileDocument)
    }

    override suspend fun getProfile(username: String): DataResult<User> = tryGetDataCall {
        val userDocument = userCollection.getDocument("profile-$username")
        userDocument?.toUserDomain() ?: throw Throwable("Data Not Found")
    }

}
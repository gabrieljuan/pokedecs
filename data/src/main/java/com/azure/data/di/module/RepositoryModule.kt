package com.azure.data.di.module

import com.azure.data.repository.PokeRepositoryImpl
import com.azure.data.repository.UserRepositoryImpl
import com.azure.domain.repository.PokeRepository
import com.azure.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPokeRepository(impl: PokeRepositoryImpl): PokeRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository
}
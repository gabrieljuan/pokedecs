package com.azure.pokedecs.module

import com.azure.data.repository.PokeRepositoryImpl
import com.azure.domain.repository.PokeRepository
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
    abstract fun bindPokeRepositoryModule(impl: PokeRepositoryImpl): PokeRepository
}
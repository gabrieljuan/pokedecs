package com.azure.pokedecs.module

import com.azure.domain.repository.PokeRepository
import com.azure.domain.repository.UserRepository
import com.azure.domain.usecase.GetPokeDetailUseCase
import com.azure.domain.usecase.GetPokeListUseCase
import com.azure.domain.usecase.GetProfileUseCase
import com.azure.domain.usecase.LoginUseCase
import com.azure.domain.usecase.RegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetPokeListUseCase(
        repository: PokeRepository
    ): GetPokeListUseCase = GetPokeListUseCase(repository)

    @Provides
    fun provideGetPokeDetailUseCase(
        repository: PokeRepository
    ): GetPokeDetailUseCase = GetPokeDetailUseCase(repository)

    @Provides
    fun provideLoginUseCase(
        repository: UserRepository
    ): LoginUseCase = LoginUseCase(repository)

    @Provides
    fun provideRegisterUseCase(
        repository: UserRepository
    ): RegisterUseCase = RegisterUseCase(repository)

    @Provides
    fun provideGetProfileUseCase(
        repository: UserRepository
    ): GetProfileUseCase = GetProfileUseCase(repository)
}
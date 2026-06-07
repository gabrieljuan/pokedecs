package com.azure.pokedecs.module

import com.azure.data.local.datasource.PokeApiLocalDataSource
import com.azure.data.local.datasource.impl.PokeApiLocalDataSourceImpl
import com.azure.data.remote.datasource.PokeApiNetworkDataSource
import com.azure.data.remote.datasource.impl.PokeApiNetworkDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindPokeNetworkDataSource(impl: PokeApiNetworkDataSourceImpl): PokeApiNetworkDataSource
    @Binds
    abstract fun bindPokeLocalDataSource(impl: PokeApiLocalDataSourceImpl): PokeApiLocalDataSource
}
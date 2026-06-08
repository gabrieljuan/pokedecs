package com.azure.data.repository

import com.azure.data.local.datasource.PokeApiLocalDataSource
import com.azure.data.remote.datasource.PokeApiNetworkDataSource
import com.azure.data.remote.mapper.toDomain
import com.azure.domain.model.Poke
import com.azure.domain.model.PokeDetail
import com.azure.domain.repository.PokeRepository
import com.azure.domain.util.DataResult
import com.azure.domain.util.mapToDomain
import javax.inject.Inject

class PokeRepositoryImpl @Inject constructor(
    private val localDataSource: PokeApiLocalDataSource,
    private val networkDataSource: PokeApiNetworkDataSource,
) : PokeRepository {
    override suspend fun getPokeList(offset: Int, limit: Int): DataResult<List<Poke>> {
        val networkResult = networkDataSource.getPokeList(offset, limit)
        if (networkResult is DataResult.Success) {
            localDataSource.savePokeList(networkResult.value, offset)
        } else {
            return localDataSource.getPokeList(offset, limit).mapToDomain { it.toDomain() }
        }
        return networkResult.mapToDomain { it.toDomain() }
    }

    override suspend fun getPokeDetail(pokeName: String): DataResult<PokeDetail> {
        val networkResult = networkDataSource.getPokeDetail(pokeName)
        if (networkResult is DataResult.Success) {
            localDataSource.savePokeDetail(networkResult.value)
        } else {
            return localDataSource.getPokeDetail(pokeName).mapToDomain { it.toDomain() }
        }
        return networkResult.mapToDomain { it.toDomain() }
    }

}
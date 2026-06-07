package com.azure.data.local.datasource

import com.azure.data.remote.response.PokeDetailResponse
import com.azure.data.remote.response.PokeListResponse
import com.azure.domain.util.DataResult

interface PokeApiLocalDataSource {
    suspend fun savePokeList(pokeList: PokeListResponse): DataResult<Unit>
    suspend fun getPokeList(offset: Int, limit: Int): DataResult<PokeListResponse>
    suspend fun savePokeDetail(pokeDetail: PokeDetailResponse): DataResult<Unit>
    suspend fun getPokeDetail(name: String): DataResult<PokeDetailResponse>
}
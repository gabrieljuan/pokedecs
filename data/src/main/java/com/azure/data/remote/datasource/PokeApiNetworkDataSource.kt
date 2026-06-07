package com.azure.data.remote.datasource

import com.azure.data.remote.response.PokeDetailResponse
import com.azure.data.remote.response.PokeListResponse
import com.azure.domain.util.DataResult

interface PokeApiNetworkDataSource {
    suspend fun getPokeList(
        offset: Int,
        limit: Int
    ): DataResult<PokeListResponse>

    suspend fun getPokeDetail(
        name: String
    ): DataResult<PokeDetailResponse>
}
package com.azure.data.remote.datasource.impl

import com.azure.data.remote.api.PokeApi
import com.azure.data.remote.datasource.PokeApiNetworkDataSource
import com.azure.data.remote.response.PokeDetailResponse
import com.azure.data.remote.response.PokeListResponse
import com.azure.domain.util.DataResult
import com.azure.domain.util.tryGetDataCall
import javax.inject.Inject

class PokeApiNetworkDataSourceImpl @Inject constructor(
    private val api: PokeApi
): PokeApiNetworkDataSource {
    override suspend fun getPokeList(offset: Int, limit: Int): DataResult<PokeListResponse> = tryGetDataCall {
        api.getPokeList(offset, limit)
    }

    override suspend fun getPokeDetail(name: String): DataResult<PokeDetailResponse> = tryGetDataCall {
        api.getPokeDetail(name)
    }
}
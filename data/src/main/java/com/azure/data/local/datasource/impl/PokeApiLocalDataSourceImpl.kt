package com.azure.data.local.datasource.impl

import com.azure.data.local.datasource.PokeApiLocalDataSource
import com.azure.data.remote.response.PokeDetailResponse
import com.azure.data.remote.response.PokeListResponse
import com.azure.domain.util.DataResult
import com.azure.domain.util.tryGetDataCall
import com.couchbase.lite.Collection
import com.couchbase.lite.MutableDocument
import kotlinx.serialization.json.Json
import javax.inject.Inject

class PokeApiLocalDataSourceImpl @Inject constructor(private val pokeCollections: Collection) : PokeApiLocalDataSource {
    override suspend fun savePokeList(pokeList: PokeListResponse): DataResult<Unit> = tryGetDataCall {
        pokeCollections.save(
            MutableDocument("poke_list").apply {
                setString("type", "poke_list")
                setString("items", Json.encodeToString(pokeList))
            }
        )
    }

    override suspend fun getPokeList(offset: Int, limit: Int): DataResult<PokeListResponse> = tryGetDataCall {
        val items = pokeCollections.getDocument("poke_list")?.getString("items").orEmpty()
        val pokeListResponse = Json.decodeFromString<PokeListResponse>(items)
        val filteredItems = pokeListResponse.results.drop(offset).take(limit)
        PokeListResponse(filteredItems)
    }

    override suspend fun savePokeDetail(pokeDetail: PokeDetailResponse): DataResult<Unit> = tryGetDataCall {
        pokeCollections.save(
            MutableDocument(pokeDetail.name).apply {
                setString("details", Json.encodeToString(pokeDetail))
            }
        )
    }

    override suspend fun getPokeDetail(name: String): DataResult<PokeDetailResponse> = tryGetDataCall {
        val details = pokeCollections.getDocument(name)?.getString("details").orEmpty()
        Json.decodeFromString<PokeDetailResponse>(details)
    }

}
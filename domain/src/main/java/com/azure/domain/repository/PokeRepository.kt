package com.azure.domain.repository

import com.azure.domain.model.Poke
import com.azure.domain.model.PokeDetail
import com.azure.domain.util.DataResult

interface PokeRepository {

    suspend fun getPokeList(offset: Int, limit: Int): DataResult<List<Poke>>

    suspend fun getPokeDetail(pokeName: String): DataResult<PokeDetail>
}
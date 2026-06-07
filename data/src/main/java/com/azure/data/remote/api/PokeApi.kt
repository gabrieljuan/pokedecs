package com.azure.data.remote.api

import com.azure.data.remote.response.PokeDetailResponse
import com.azure.data.remote.response.PokeListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {

    @GET("pokemon")
    suspend fun getPokeList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): PokeListResponse

    @GET("pokemon/{name}")
    suspend fun getPokeDetail(
        @Path("name") name: String
    ): PokeDetailResponse
}
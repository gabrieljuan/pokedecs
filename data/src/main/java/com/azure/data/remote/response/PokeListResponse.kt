package com.azure.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class PokeListResponse(val results: List<PokeListItem> = listOf())

@Serializable
data class PokeListItem(
    val name: String = ""
)
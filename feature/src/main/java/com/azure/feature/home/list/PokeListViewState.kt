package com.azure.feature.home.list

import com.azure.domain.model.Poke

data class PokeListViewState(
    val query: String = "",
    val isLoading: Boolean = true,
    val pokeList: List<Poke> = listOf(),
    val isFromCache: Boolean = false,
    val errorMessage: String? = null,
)
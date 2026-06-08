package com.azure.feature.home

import com.azure.domain.model.Poke

data class PokeListViewState(
    val isLoading: Boolean = true,
    val pokeList: List<Poke> = listOf(),
    val isFromCache: Boolean = false,
    val isNetworkError: Boolean = false,
)
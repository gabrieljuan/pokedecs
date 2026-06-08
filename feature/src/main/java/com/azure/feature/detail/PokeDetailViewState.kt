package com.azure.feature.detail

import com.azure.domain.model.PokeDetail

data class PokeDetailViewState(
    val pokeDetail: PokeDetail = PokeDetail(),
    val isFromCache: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)

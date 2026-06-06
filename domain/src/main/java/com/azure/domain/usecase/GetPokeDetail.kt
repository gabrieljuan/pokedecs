package com.azure.domain.usecase

import com.azure.domain.repository.PokeRepository

class GetPokeDetail(
    private val repository: PokeRepository
) {
    suspend operator fun invoke(pokeName: String) =
        repository.getPokeDetail(pokeName)
}
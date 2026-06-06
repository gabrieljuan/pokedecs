package com.azure.domain.usecase

import com.azure.domain.repository.PokeRepository

class GetPokeListUseCase(
    private val repository: PokeRepository
) {
    suspend operator fun invoke(offset: Int) =
        repository.getPokeList(
            offset = offset,
            limit = 10
        )
}
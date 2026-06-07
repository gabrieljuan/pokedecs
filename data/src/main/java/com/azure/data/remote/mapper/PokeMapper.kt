package com.azure.data.remote.mapper

import com.azure.data.remote.response.Ability
import com.azure.data.remote.response.PokeDetailResponse
import com.azure.data.remote.response.PokeListResponse
import com.azure.domain.model.Poke
import com.azure.domain.model.PokeDetail

fun PokeDetailResponse.toDomain(): PokeDetail =
    PokeDetail(
        name = name,
        height = height,
        weight = weight,
        element = types.firstOrNull()?.name ?: "",
        spriteUrl = sprites.firstOrNull()?.frontDefault ?: "",
        abilities = abilities.toDomain()
    )

fun List<Ability>.toDomain(): List<com.azure.domain.model.Ability> = map { com.azure.domain.model.Ability(it.name) }

fun PokeListResponse.toDomain(): List<Poke> = results.map { Poke(it.name) }
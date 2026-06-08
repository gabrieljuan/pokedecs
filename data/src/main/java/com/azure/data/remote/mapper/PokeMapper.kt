package com.azure.data.remote.mapper

import com.azure.data.remote.response.AbilityWrapper
import com.azure.data.remote.response.PokeDetailResponse
import com.azure.data.remote.response.PokeListResponse
import com.azure.domain.model.Poke
import com.azure.domain.model.PokeDetail

fun PokeDetailResponse.toDomain(): PokeDetail =
    PokeDetail(
        name = name.replaceFirstChar { it.uppercaseChar() },
        height = height,
        weight = weight,
        element = types.firstOrNull()?.type?.name?.replaceFirstChar { it.uppercaseChar() } ?: "",
        spriteUrl = sprites.frontDefault,
        abilities = abilities.toDomain()
    )

fun List<AbilityWrapper>.toDomain(): List<com.azure.domain.model.Ability> = map { com.azure.domain.model.Ability(it.ability.name.replaceFirstChar { char -> char.uppercaseChar() }) }

fun PokeListResponse.toDomain(): List<Poke> = results.map { Poke(it.name.replaceFirstChar { char -> char.uppercaseChar() }) }
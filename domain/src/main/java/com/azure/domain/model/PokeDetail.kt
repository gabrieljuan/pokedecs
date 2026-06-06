package com.azure.domain.model

data class PokeDetail(
    val name: String = "",
    val height: Int = 0,
    val weight: Int = 0,
    val abilities: List<Ability> = listOf(),
)

data class Ability(
    val name: String = ""
)
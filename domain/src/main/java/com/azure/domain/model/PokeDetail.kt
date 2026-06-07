package com.azure.domain.model

data class PokeDetail(
    val name: String = "",
    val height: Int = 0,
    val weight: Int = 0,
    val element: String = "",
    val spriteUrl: String = "",
    val abilities: List<Ability> = listOf(),
)
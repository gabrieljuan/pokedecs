package com.azure.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokeDetailResponse(
    val name: String = "",
    val abilities: List<Ability> = listOf(),
    val height: Int = 0,
    val weight: Int = 0,
    val sprites: List<Sprite> = listOf(),
    val types: List<Element> = listOf(),
)

@Serializable
data class Ability(
    val name: String = ""
)

@Serializable
data class Sprite(
    @SerialName("front_default")
    val frontDefault: String = "",
)

@Serializable
data class Element(
    val name: String = "",
)
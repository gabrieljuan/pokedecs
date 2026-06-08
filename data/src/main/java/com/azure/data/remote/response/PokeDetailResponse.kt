package com.azure.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokeDetailResponse(
    val name: String = "",
    val abilities: List<AbilityWrapper> = listOf(),
    val height: Int = 0,
    val weight: Int = 0,
    val sprites: Sprite = Sprite(),
    val types: List<TypeWrapper> = listOf(),
)

@Serializable
data class AbilityWrapper(
    val ability: Ability = Ability()
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
data class TypeWrapper(
    val type: Element = Element()
)

@Serializable
data class Element(
    val name: String = "",
)
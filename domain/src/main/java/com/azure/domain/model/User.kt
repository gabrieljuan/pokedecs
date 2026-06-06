package com.azure.domain.model

data class User(
    val username: String,
    val phone: String,
    val email: String,
    val about: String,
)
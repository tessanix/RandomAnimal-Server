package com.tmcoding.data

import kotlinx.serialization.Serializable

@Serializable
data class Animal(
    val name: String,
    val description: String,
    val imageUrl: String
)

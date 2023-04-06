package com.tmcoding.data

import kotlinx.serialization.Serializable

@Serializable
data class Animal(
    var name: String? = null,
    var description: String? = null,
    var imageUrl: String? = null
)
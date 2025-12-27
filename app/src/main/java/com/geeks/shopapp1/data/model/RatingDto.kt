package com.geeks.shopapp1.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RatingDto(
    @SerialName("rate")
    val rate: Double?, // 3.9
    @SerialName("count")
    val count: Int? // 120
)
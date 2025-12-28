package com.geeks.shopapp1.data.model

import android.adservices.adid.AdId
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

@Serializable
data class CartRequestDto(
    @SerialName("userId") val userId: Int = 1,
    @SerialName("data") val data: String = "2025-12-20",
    @SerialName("products") val products: List<CartProductDto>
)

@Serializable
data class CartProductDto(
    @SerialName("productId") val productId: Int,
    @SerialName("quantity") val quantity: Int,
)

@Serializable
data class CartResponseDto(
    @SerialName("id") val id: Int
)

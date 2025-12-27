package com.geeks.shopapp1.data.model


import  kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class  ProductDto(
    @SerialName("id")
    val id: Int?, // 1
    @SerialName("title")
    val title: String?, // Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops
    @SerialName("price")
    val price: Double?, // 109.95
    @SerialName("description")
    val description: String?, // Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday
    @SerialName("category")
    val category: String?, // men's clothing
    @SerialName("image")
    val image: String?, // https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_t.png
    @SerialName("rating")
    val rating: RatingDto?
)
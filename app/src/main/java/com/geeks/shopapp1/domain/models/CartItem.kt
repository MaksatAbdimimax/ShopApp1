package com.geeks.shopapp1.domain.models

data class CartItem (
    val product: Product,
    val quantity: Int = 1
)
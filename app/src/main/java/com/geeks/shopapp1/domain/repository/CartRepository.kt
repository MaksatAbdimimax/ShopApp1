package com.geeks.shopapp1.domain.repository

import com.geeks.shopapp1.domain.models.CartItem
import com.geeks.shopapp1.domain.models.Product
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    val cartItems: Flow<List<CartItem>>

    suspend fun addToCart(product: Product)

    suspend fun clearCart()

    suspend fun checout(): Result<String>
}
package com.geeks.shopapp1.domain.usecases

import com.geeks.shopapp1.domain.models.CartItem
import com.geeks.shopapp1.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow

class GetCartItemsUseCase(
    private val cartRepository: CartRepository
) {
    operator fun invoke(): Flow<List<CartItem>>{
        return cartRepository.cartItems
    }
}
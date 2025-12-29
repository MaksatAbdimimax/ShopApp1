package com.geeks.shopapp1.domain.usecases

import com.geeks.shopapp1.domain.models.Product
import com.geeks.shopapp1.domain.repository.CartRepository
import com.geeks.shopapp1.domain.repository.ProductRepository

class AddToCartUseCase(
    private val cartRepository: CartRepository
){
    suspend operator fun invoke(product: Product){
        cartRepository.addToCart(product )
    }
}
package com.geeks.shopapp1.domain.usecases

import com.geeks.shopapp1.domain.repository.CartRepository

class CheckoutUseCase(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(): Result<String>{
        return cartRepository.checout()
    }
}
package com.geeks.shopapp1.domain.usecases

import com.geeks.shopapp1.domain.repository.CartRepository

class ClearCartUseCase(

private val cartRepository: CartRepository
){
        suspend operator fun invoke(){
            cartRepository.clearCart()
    }
}
package com.geeks.shopapp1.domain.usecases

import com.geeks.shopapp1.domain.models.Product
import com.geeks.shopapp1.domain.repository.CartRepository
import com.geeks.shopapp1.domain.repository.ProductRepository

class GetDetailProductUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(productId: Int): Product{
        return repository.getProductById(productId)
    }
}
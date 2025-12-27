package com.geeks.shopapp1.domain.usecases

import com.geeks.shopapp1.domain.models.Product
import com.geeks.shopapp1.domain.repository.ProductRepository

class GetProductUseCase (
    private val repository: ProductRepository
){
    suspend operator fun invoke(): List<Product>{
        return repository.getProducts()
    }

}
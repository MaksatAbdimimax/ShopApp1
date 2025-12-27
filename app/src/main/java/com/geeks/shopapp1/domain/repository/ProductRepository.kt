package com.geeks.shopapp1.domain.repository

import com.geeks.shopapp1.domain.models.Product

interface ProductRepository {

    suspend fun getProducts(): List<Product>

    suspend fun getProductById(id: Int): Product
}

package com.geeks.shopapp1.data.repository

import com.geeks.shopapp1.data.datasource.StoreApi
import com.geeks.shopapp1.data.mappers.toDomain
import com.geeks.shopapp1.domain.models.Product
import com.geeks.shopapp1.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val api: StoreApi
) : ProductRepository {

    override suspend fun getProducts(): List<Product> =
        api.getAllProducts().map { it.toDomain() }

    override suspend fun getProductById(id: Int): Product =
        api.getProductById(id).toDomain()
}

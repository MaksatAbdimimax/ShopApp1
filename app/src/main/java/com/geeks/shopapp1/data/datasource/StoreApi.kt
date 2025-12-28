package com.geeks.shopapp1.data.datasource

import com.geeks.shopapp1.data.model.CartRequestDto
import com.geeks.shopapp1.data.model.CartResponseDto
import com.geeks.shopapp1.data.model.ProductDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface StoreApi {

    @GET("products")
    suspend fun getAllProducts(): List<ProductDto>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id")id: Int): ProductDto

    @POST("carts")
    suspend fun checout(@Body cart: CartRequestDto): CartResponseDto
}
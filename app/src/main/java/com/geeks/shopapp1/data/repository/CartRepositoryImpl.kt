package com.geeks.shopapp1.data.repository

import com.geeks.shopapp1.data.datasource.StoreApi
import com.geeks.shopapp1.data.model.CartProductDto
import com.geeks.shopapp1.data.model.CartRequestDto
import com.geeks.shopapp1.domain.models.CartItem
import com.geeks.shopapp1.domain.models.Product
import com.geeks.shopapp1.domain.repository.CartRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CartRepositoryImpl(
    private val api: StoreApi
): CartRepository {
    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    override val cartItems = _cartItems.asStateFlow()

    override suspend fun addToCart(product: Product) {
        _cartItems.update {  currentList ->
            val existing = currentList.find {it.product.id == product.id}
            if (existing != null){
                currentList.map { item ->
                    if (item.product.id == product.id) item.copy(quantity = item.quantity + 1)
                    else item
                }
            } else{
                currentList + CartItem(product, quantity = 1)
            }

        }
    }

    override suspend fun clearCart() {
        _cartItems.value = emptyList()
    }

    override suspend fun checout(): Result<String> {
        return try {
            val items = _cartItems.value
            val dtos = items.map { CartProductDto(it.product.id, it.quantity )}
            val request = CartRequestDto(products = dtos)

            val resp = api.checout(request)
            Result.success("Заказ №${resp.id} успешно оформлен")

        }catch (e: Exception){
            Result.failure(e)
        }
    }


}
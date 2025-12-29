package com.geeks.shopapp1.ui.fragments.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geeks.shopapp1.domain.repository.CartRepository
import com.geeks.shopapp1.domain.usecases.CheckoutUseCase
import com.geeks.shopapp1.domain.usecases.ClearCartUseCase
import com.geeks.shopapp1.domain.usecases.GetCartItemsUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
////
//
class CartViewModel (
    private val getCartItemsUseCase: GetCartItemsUseCase,
    private val checkoutUseCase: CheckoutUseCase,
    private val clearCartUseCase: ClearCartUseCase,

    private val repository: CartRepository
): ViewModel() {


    val items = getCartItemsUseCase()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val total  = items.map { list ->
        list.sumOf {
            val price  = it.product.price
            price * it.quantity
        }

    }.stateIn(viewModelScope, SharingStarted.Lazily, 0.0)

    fun checout(){
        viewModelScope.launch {
            val result = checkoutUseCase()

            result.onSuccess {
                clearCartUseCase()
            }
        }
    }
}
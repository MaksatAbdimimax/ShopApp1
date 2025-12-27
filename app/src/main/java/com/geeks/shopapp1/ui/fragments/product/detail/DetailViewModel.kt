package com.geeks.shopapp1.ui.fragments.product.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geeks.shopapp1.domain.models.Product
import com.geeks.shopapp1.domain.repository.ProductRepository

import com.geeks.shopapp1.ui.models.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: ProductRepository
) : ViewModel() {

    private val _state =
        MutableStateFlow<UiState<Product>>(UiState.Loaging)
    val state = _state.asStateFlow()

    fun loadProduct(id: Int) {
        viewModelScope.launch {
            try {
                _state.value = UiState.Loaging
                val product = repository.getProductById(id)
                _state.value = UiState.Succes(product)
            } catch (e: Exception) {
                _state.value = UiState.Error(e.message ?: "Ошибка")
            }
        }
    }
}

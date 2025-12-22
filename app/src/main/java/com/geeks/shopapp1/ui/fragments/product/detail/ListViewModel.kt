package com.geeks.shopapp1.ui.fragments.product.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geeks.shopapp1.data.model.ProductDto
import com.geeks.shopapp1.repository.ProductRepository
import com.geeks.shopapp1.ui.models.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {
    private val repasitory = ProductRepository()

    private val  _state = MutableStateFlow<UiState<List<ProductDto>>>(UiState.Loaging)
    val  state : StateFlow<UiState<List<ProductDto>>> = _state.asStateFlow()
    init {
        loadProducts()
    }
    fun loadProducts () {
        viewModelScope.launch{
          _state.value = UiState.Loaging
          try {
              val products = repasitory.getProducts() //getAllProducts
              _state.value = UiState.Succes(data = products) //data =
          } catch (e: Exception) {
              _state.value = UiState.Error(e.message?: "")
          }
        }
    }

}
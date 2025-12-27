package com.geeks.shopapp1.ui.fragments.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geeks.shopapp1.data.model.ProductDto
import com.geeks.shopapp1.domain.models.Product
import com.geeks.shopapp1.domain.usecases.GetProductUseCase
import com.geeks.shopapp1.ui.models.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ListViewModel(
    private val getProductUseCase: GetProductUseCase
) : ViewModel() {
   //private val repasitory = ProductRepository()

    private val  _state = MutableStateFlow<UiState<List<Product>>>(UiState.Loaging)
    val  state : StateFlow<UiState<List<Product>>> = _state.asStateFlow()
    init {
        loadProducts()
    }
    fun loadProducts () {
        viewModelScope.launch {
          _state.value = UiState.Loaging
          try {
              //repasitory.getProducts() //getAllProducts
              val products = getProductUseCase()
              _state.value = UiState.Succes(data = products) //data =
          } catch (e: Exception) {
              _state.value = UiState.Error(e.message?: "")
          }
        }
    }

}
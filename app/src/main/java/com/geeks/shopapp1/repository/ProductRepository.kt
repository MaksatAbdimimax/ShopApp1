package com.geeks.shopapp1.repository

import android.widget.Toast
import androidx.lifecycle.Lifecycle
import com.geeks.shopapp1.data.api.RetrofitService
import com.geeks.shopapp1.data.model.ProductDto
import com.geeks.shopapp1.ui.models.UiState

class ProductRepository {

    suspend fun getProducts(): List<ProductDto>{
        return RetrofitService.api.getAllProducts()
    }

    suspend fun getProductById(id: Int): ProductDto {
        return RetrofitService.api.getProductById(id)
    }

}
// ProductListFragment: ***
// ProductAdapter
// 8:44

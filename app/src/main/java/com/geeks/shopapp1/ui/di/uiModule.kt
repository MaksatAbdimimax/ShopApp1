package com.geeks.shopapp1.ui.di

import com.geeks.shopapp1.ui.fragments.cart.CartViewModel
import com.geeks.shopapp1.ui.fragments.product.ListViewModel
import com.geeks.shopapp1.ui.fragments.product.detail.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {

    viewModel {
        ListViewModel(
            getProductUseCase = get(),
            productRepository = get(),
            cartRepository = get()
        )
    }

    viewModel {
        DetailViewModel(get())
    }

    viewModel {
        CartViewModel(get(),get(),get(),get())
    }
}


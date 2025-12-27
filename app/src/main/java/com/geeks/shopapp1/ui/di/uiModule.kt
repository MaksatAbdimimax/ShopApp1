package com.geeks.shopapp1.ui.di

import com.geeks.shopapp1.ui.fragments.product.ListViewModel
import com.geeks.shopapp1.ui.fragments.product.detail.DetailViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { ListViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}

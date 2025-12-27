package com.geeks.shopapp1.domain.di

import com.geeks.shopapp1.domain.usecases.GetProductUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetProductUseCase(repository = get(  )) }
}
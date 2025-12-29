package com.geeks.shopapp1.domain.di

import com.geeks.shopapp1.domain.usecases.AddToCartUseCase
import com.geeks.shopapp1.domain.usecases.CheckoutUseCase
import com.geeks.shopapp1.domain.usecases.ClearCartUseCase
import com.geeks.shopapp1.domain.usecases.GetCartItemsUseCase
import com.geeks.shopapp1.domain.usecases.GetDetailProductUseCase
import com.geeks.shopapp1.domain.usecases.GetProductUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetProductUseCase(repository = get(  )) }
    factory { GetDetailProductUseCase(repository = get(  )) }

    factory { AddToCartUseCase(cartRepository = get(  )) }
    factory { CheckoutUseCase(cartRepository = get(  )) }
    factory { ClearCartUseCase(cartRepository = get(  )) }
    factory { GetCartItemsUseCase(cartRepository = get(  )) }


}
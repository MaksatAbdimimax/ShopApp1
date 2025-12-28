package com.geeks.shopapp1.data.di

import com.geeks.shopapp1.data.datasource.RetrofitService
import com.geeks.shopapp1.data.datasource.StoreApi
import com.geeks.shopapp1.data.repository.CartRepositoryImpl
import com.geeks.shopapp1.data.repository.ProductRepositoryImpl
import com.geeks.shopapp1.domain.repository.CartRepository
import com.geeks.shopapp1.domain.repository.ProductRepository

import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

private val json = Json {
    ignoreUnknownKeys = true
    coerceInputValues = true
}

private const val BASE_URl = "https://fakestoreapi.com/"

val dataModule = module {

    single {
        json.asConverterFactory(contentType = "application/json".toMediaType())
    }

    /*single {
        HttpLoggingInterceptor().apply{
            level = HttpLoggingInterceptor.Level.BODY
    }*/

    single <Interceptor>(named("logging")){
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor (get<Interceptor>(named("logging")))
            .build()
    }



    single {
        Retrofit.Builder()
            .baseUrl(BASE_URl)
            .client(get())

            .addConverterFactory(get())
            .build()
            .create(StoreApi::class.java )
    }

    single <ProductRepository>{ ProductRepositoryImpl(get()) }
    single<CartRepository> { CartRepositoryImpl(api = get()) }
}
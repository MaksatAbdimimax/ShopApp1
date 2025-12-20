package com.geeks.shopapp1.data.api

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object RetrofitService {
    private const val BASE_URl = "https://fakestoreapi.com/"

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
        
    }

    private val loggingInterseptor = HttpLoggingInterceptor().apply{
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterseptor )
        .build()

    private val contentType = "application/json".toMediaType()

    val api: StoreApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URl)
            .client(client)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(StoreApi::class.java)
    }

}
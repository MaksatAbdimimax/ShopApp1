package com.geeks.shopapp1.domain.models


data class  Product(

    val id: Int, // 1
    val title: String, // Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops
    val price: Double, // 109.95
    val description: String, // Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday
    val category: String, // men's clothin
    val image: String, // https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_t.png
    val rating: Rating
)


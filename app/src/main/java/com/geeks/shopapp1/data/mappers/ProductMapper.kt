package com.geeks.shopapp1.data.mappers

import com.geeks.shopapp1.data.model.ProductDto
import com.geeks.shopapp1.data.model.RatingDto
import com.geeks.shopapp1.domain.models.Product
import com.geeks.shopapp1.domain.models.Rating

fun ProductDto.toDomain(): Product {
    return Product(
        id = this.id ?: -1,
        title = this.title ?: "",
        price = this.price ?: 0.0,
        description = this.description ?: "",
        category = this.category ?: "",
        image = this.image ?: "",
        rating = this.rating?.toDomain() ?: Rating.empty()
    )
}

fun RatingDto.toDomain(): Rating {
    return Rating(
        rate = this.rate ?: 0.0,
        count = this.count ?: 0
    )
}
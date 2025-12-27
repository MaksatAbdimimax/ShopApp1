package com.geeks.shopapp1.domain.models


import com.geeks.shopapp1.data.model.RatingDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


data class Rating(

    val rate: Double?, // 3.9
    val count: Int? // 120
){
    companion object{
        fun empty(): Rating{
            return Rating(
                rate = 0.0,
                count = 0
            )
        }
    }
}
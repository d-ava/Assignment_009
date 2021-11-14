package com.example.assignment_009_003.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShopItem(
    @Json(name = "title")
    val title: String,
    @Json(name = "cover")
    val cover: String,
    @Json(name = "price")
    val price: String,
    @Json(name = "liked")
    val liked: Boolean
)
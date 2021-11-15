package com.example.assignment_009_003.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity (tableName = "shop_table")
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
){
    @PrimaryKey(autoGenerate = true) var uid: Int = 0
}
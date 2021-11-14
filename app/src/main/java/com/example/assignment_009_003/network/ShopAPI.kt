package com.example.assignment_009_003.network

import com.example.assignment_009_003.model.ShopItem
import retrofit2.Response
import retrofit2.http.GET

interface ShopAPI {

    @GET("05d71804-4628-4269-ac03-f86e9960a0bb")
    suspend fun getShopItems():Response<List<ShopItem>>
}
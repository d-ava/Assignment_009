package com.example.assignment_009_003.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkClient {

    private const val BASE_URL = "https://run.mocky.io/v3/"


    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)

            .addConverterFactory(MoshiConverterFactory.create(moshi()))
            .build()
    }


    private fun moshi() =
        Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()


    val api: ShopAPI by lazy {
        retrofit.create(ShopAPI::class.java)
    }
}
package com.example.assignment_009_003.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment_009_003.db.ShopDao
import com.example.assignment_009_003.db.ShopDatabase

import com.example.assignment_009_003.model.ShopItem
import com.example.assignment_009_003.network.NetworkClient
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShopViewModel : ViewModel() {



    private val _shop = MutableSharedFlow<List<ShopItem>>()
    val shop: SharedFlow<List<ShopItem>> = _shop

    private var shopDao:ShopDao = ShopDatabase.db.shopDao()

    fun loadShop(){
        viewModelScope.launch {
            withContext(IO){
                val response = NetworkClient.api.getShopItems()
                val body = response.body()
                if (response.isSuccessful && body != null)
                    _shop.emit(body)
                addShopTodb(body!!)
            }
        }
    }

    fun addShopTodb(shopItems: List<ShopItem>){
        viewModelScope.launch(IO) {
            shopDao.addShopItems(*shopItems.toTypedArray())
        }
    }
}
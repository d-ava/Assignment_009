package com.example.assignment_009_003.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    fun loadShop(){
        viewModelScope.launch {
            withContext(IO){
                val response = NetworkClient.api.getShopItems()
                val body = response.body()
                if (response.isSuccessful && body != null)
                    _shop.emit(body)
            }
        }
    }

}
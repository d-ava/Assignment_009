package com.example.assignment_009_003.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.assignment_009_003.model.ShopItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ShopDao {

@Insert (onConflict = OnConflictStrategy.REPLACE)
suspend fun addShopItems (vararg shopItem: ShopItem)

@Query ("SELECT * FROM shop_table")
fun readAllData(): Flow<List<ShopItem>>

}
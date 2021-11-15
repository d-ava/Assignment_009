package com.example.assignment_009_003.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.assignment_009_003.App
import com.example.assignment_009_003.model.ShopItem

@Database(entities = [ShopItem::class], version = 1, exportSchema = false)
abstract class ShopDatabase : RoomDatabase() {

    abstract fun shopDao(): ShopDao

    /*companion object{
        @Volatile
        private var INSTANCE: ShopDatabase? = null

        fun getDatabase(context: Context): ShopDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    ShopDatabase::class.java,
                    "app_database")
                    .createFromAsset("database/bus_schedule.db")
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }*/


    companion object {
        val db by lazy {
            Room.databaseBuilder(
                App.appContext!!,
                ShopDatabase::class.java,
                "Shop_database"
            ).build()
        }
    }




}
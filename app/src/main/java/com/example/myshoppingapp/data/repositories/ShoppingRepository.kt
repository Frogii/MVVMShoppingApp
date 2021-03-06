package com.example.myshoppingapp.data.repositories

import com.example.myshoppingapp.data.db.ShoppingDatabase
import com.example.myshoppingapp.data.db.entities.ShoppingItem

class ShoppingRepository(private val db: ShoppingDatabase) {

    suspend fun addItem(item: ShoppingItem) = db.getSoppingDao().addItem(item)

    fun getAllItems() = db.getSoppingDao().getAllItems()

    suspend fun delete(item: ShoppingItem) = db.getSoppingDao().delete(item)

    suspend fun deleteAllItems() = db.getSoppingDao().deleteAllItems()

}
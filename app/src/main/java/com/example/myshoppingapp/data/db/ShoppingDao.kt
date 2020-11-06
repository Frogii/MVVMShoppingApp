package com.example.myshoppingapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myshoppingapp.data.db.entities.ShoppingItem

@Dao
interface ShoppingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItem(item: ShoppingItem)

    @Query("SELECT * FROM shopping_items")
    fun getAllItems(): LiveData<List<ShoppingItem>>

    @Delete
    suspend fun delete(item: ShoppingItem)

    @Query("DELETE FROM shopping_items")
    suspend fun deleteAllItems()
}
package com.example.myshoppingapp.ui

import androidx.lifecycle.ViewModel
import com.example.myshoppingapp.data.db.entities.ShoppingItem
import com.example.myshoppingapp.data.repositories.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingViewModel(private val repository: ShoppingRepository) : ViewModel() {

    fun addItem(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.addItem(item)
    }

    fun getAllItems() = repository.getAllItems()

    fun delete(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.delete(item)
    }
}
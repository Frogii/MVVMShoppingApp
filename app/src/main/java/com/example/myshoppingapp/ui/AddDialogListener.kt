package com.example.myshoppingapp.ui

import com.example.myshoppingapp.data.db.entities.ShoppingItem

interface AddDialogListener {
    fun addButtonClicked(item: ShoppingItem)
}
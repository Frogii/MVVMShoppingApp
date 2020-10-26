package com.example.myshoppingapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myshoppingapp.R
import com.example.myshoppingapp.adapter.ShoppingRecAdapter
import com.example.myshoppingapp.data.db.ShoppingDatabase
import com.example.myshoppingapp.data.db.entities.ShoppingItem
import com.example.myshoppingapp.data.repositories.ShoppingRepository
import kotlinx.android.synthetic.main.activity_main.*

var currentNumOfItems = 0

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)
        val viewModel = ViewModelProviders.of(this, factory).get(ShoppingViewModel::class.java)

        val adapter = ShoppingRecAdapter(listOf(), viewModel)

        rvShoppingItems.layoutManager = LinearLayoutManager(this)
        rvShoppingItems.adapter = adapter

        viewModel.getAllItems().observe(this, Observer {
            currentNumOfItems = it.size
            adapter.items = it.reversed()
            adapter.notifyDataSetChanged()
            Log.d("TAG", currentNumOfItems.toString())
        })

        fab.setOnClickListener {
            AddShoppingItemDialog(this, object : AddDialogListener {
                override fun addButtonClicked(item: ShoppingItem) {
                    viewModel.addItem(item)
                }
            }).show()
        }
    }
}
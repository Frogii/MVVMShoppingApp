package com.example.myshoppingapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myshoppingapp.R
import com.example.myshoppingapp.data.db.entities.ShoppingItem
import com.example.myshoppingapp.ui.ShoppingViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.shopping_item.view.*

class ShoppingRecAdapter(var items: List<ShoppingItem>, private val viewModel: ShoppingViewModel) :
    RecyclerView.Adapter<ShoppingRecAdapter.ShoppingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        return ShoppingViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.shopping_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val item = items[position]
        val numberOfItem = items.lastIndex - position + 1
        holder.itemView.tvItem.text = item.name
        holder.itemView.tvNumberOfItem.text = numberOfItem.toString()

        holder.itemView.ivDelete.setOnClickListener {
            viewModel.delete(item)
            Snackbar.make(holder.itemView, "Item deleted", Snackbar.LENGTH_LONG).apply {
                setAction("UNDO") {
                    viewModel.addItem(item)
                }
                    .show()
            }
        }

        when(item.isBought){
            true -> {
                holder.itemView.setBackgroundColor(holder.itemView.resources.getColor(R.color.checkEnable))
                holder.itemView.checkBuy.isChecked = true
            }
            false -> {
                holder.itemView.setBackgroundColor(holder.itemView.resources.getColor(R.color.checkDisable))
                holder.itemView.checkBuy.isChecked = false
            }
        }

        holder.itemView.checkBuy.setOnClickListener {
            when (holder.itemView.checkBuy.isChecked) {
                true -> {
                    item.isBought = true
                    viewModel.addItem(item)
                }
                false -> {
                    item.isBought = false
                    viewModel.addItem(item)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ShoppingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}
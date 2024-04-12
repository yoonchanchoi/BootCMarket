package com.example.bootcmarket

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.model.Product

class ProductAdapter(
    private val context: Context,
    private val productAdapterListener: ProductAdapterListener,
    private val products: ArrayList<Product>
): RecyclerView.Adapter<ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder.from(parent, productAdapterListener)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(context,products[position])

    }
}
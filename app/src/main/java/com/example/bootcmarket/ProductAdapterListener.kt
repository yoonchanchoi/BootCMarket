package com.example.bootcmarket

import com.example.model.Product

interface ProductAdapterListener {
    fun onItemClick(product: Product)

    fun onItemLognClick(position: Int): Boolean
}
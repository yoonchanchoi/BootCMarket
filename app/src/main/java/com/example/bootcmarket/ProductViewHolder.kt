package com.example.bootcmarket

import android.icu.text.DecimalFormat
import androidx.recyclerview.widget.RecyclerView
import com.example.bootcmarket.databinding.ItemProductBinding
import com.example.model.Product

class ProductViewHolder(
    private val binding: ItemProductBinding
):RecyclerView.ViewHolder(binding.root) {

    fun bind(product: Product, productAdapterListener: ProductAdapterListener){
        val formatter = DecimalFormat("#,###")
        binding.iv.setImageResource(product.img)
        binding.tvItemTitle.text = product.name
        binding.tvItemLocation.text = product.location
        binding.tvItemPrice.text = formatter.format(product.price) + "원"
        binding.tvFavoriteCnt.text = product.favorite.toString()
        binding.tvChatCnt.text = product.chat.toString()

        binding.itemProduct.setOnClickListener { productAdapterListener.onItemClick(product) }
        binding.itemProduct.setOnLongClickListener {
            productAdapterListener.onItemLognClick(adapterPosition)
        }
    }
}
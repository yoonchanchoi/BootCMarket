package com.example.bootcmarket

import android.content.Context
import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bootcmarket.databinding.ItemProductBinding
import com.example.model.Product

class ProductViewHolder(
    private val binding: ItemProductBinding,
    private val onProductItemClick: ProductAdapterListener
):RecyclerView.ViewHolder(binding.root) {


    init {
        binding.root.setOnClickListener {
            onProductItemClick
        }
    }

    fun bind(context:Context ,product: Product){
        val formatter = DecimalFormat("#,###")
        val resId = context.resources.getIdentifier(product.img,"drawable",context.packageName)
        binding.iv.setImageResource(resId)
        binding.tvItemTitle.text = product.name
        binding.tvItemLocation.text = product.location
        binding.tvItemPrice.text = formatter.format(product.price)
        binding.tvFavoriteCnt.text = product.favorite.toString()
        binding.tvChatCnt.text = product.chat.toString()
    }



    companion object{
        fun from(
            parent: ViewGroup,
            onProductItemClick: ProductAdapterListener
        ): ProductViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemProductBinding.inflate(layoutInflater, parent, false)
            return ProductViewHolder(binding, onProductItemClick)
        }
    }


}
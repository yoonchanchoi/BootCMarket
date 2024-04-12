package com.example.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Product(
    val number: Int = -1,
    val img: String = "",
    val name: String = "",
    val content: String = "",
    val seller: String = "",
    val price: Int = 0,
    val location: String = "",
    val favorite: Int = 0,
    val chat: Int = 0
) : Parcelable

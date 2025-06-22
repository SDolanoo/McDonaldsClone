package com.example.mcdonaldsclone.features.menu.model

import androidx.annotation.DrawableRes
import com.example.mcdonaldsclone.core.database.model.Category

data class Drinks(
    val id: Long,
    val name: String,
    @DrawableRes val imageResId: Int,
    val priceSmall: Double,
    val priceMedium: Double,
    val priceLarge: Double,
    val categoryId: Long
)
package com.example.mcdonaldsclone.core.database.model.archiveModel

import androidx.annotation.DrawableRes

data class Drinks(
    val id: Long,
    val name: String,
    @DrawableRes val imageResId: Int,
    val priceSmall: Double,
    val priceMedium: Double,
    val priceLarge: Double,
    val isSizeChagable: Boolean,
    val categoryId: Long,
    val subCategoryId: Long
)
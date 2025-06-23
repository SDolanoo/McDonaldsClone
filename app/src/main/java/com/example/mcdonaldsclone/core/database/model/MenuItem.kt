package com.example.mcdonaldsclone.core.database.model

import androidx.annotation.DrawableRes

data class MenuItem(
    val id: Long,
    val name: String,
    @DrawableRes val imageResId: Int,
    val categoryId: Long,
    val subCategoryId: Long,
    val basePrice: Double,

    // Optional/Specific fields
    val isSizeChangable: Boolean? = null,
    val priceMedium: Double? = null,
    val priceLarge: Double? = null,
    val setPrice: Double? = null,
    val isSetAvailable: Boolean? = null,
    val saucesAvailable: Boolean? = null,
    val isAvailable: Boolean? = true,
    val spiceLevel: Int? = null,
)
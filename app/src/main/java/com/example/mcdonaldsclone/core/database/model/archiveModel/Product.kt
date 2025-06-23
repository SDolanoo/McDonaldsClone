package com.example.mcdonaldsclone.core.database.model.archiveModel

import androidx.annotation.DrawableRes

data class Product(
    val id: Long,
    val name: String,
    @DrawableRes val imageResId: Int,
    val price: Double,
    val categoryId: Long,
    val subCategoryId: Long,
    val isSetAvailable: Boolean,
    val setPrice: Double = 0.00,
    val saucesAvailable: Boolean = false,
    val isAvailable: Boolean = true,
)
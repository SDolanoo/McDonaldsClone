package com.example.mcdonaldsclone.features.menu.model

import androidx.annotation.DrawableRes

data class Product(
    val id: Long,
    val name: String,
    val description: String,
    @DrawableRes val imageResId: Int,
    val price: Double,
    val categoryId: Int,
    val isAvailable: Boolean = true,
    val subCategoryId: Long?
)
package com.example.mcdonaldsclone.features.menu.model

import androidx.annotation.DrawableRes

data class Sauce(
    val id: Long,
    val name: String,
    @DrawableRes val imageResId: Int,
    val price: Double,
    val categoryId: Long,
    val subCategoryId: Long,
    val spiceLevel: Int = 0,
)
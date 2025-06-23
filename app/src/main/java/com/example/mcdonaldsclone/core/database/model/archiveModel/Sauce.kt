package com.example.mcdonaldsclone.core.database.model.archiveModel

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
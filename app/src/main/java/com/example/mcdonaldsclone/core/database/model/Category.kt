package com.example.mcdonaldsclone.core.database.model

import androidx.annotation.DrawableRes

data class Category(
    val id: Long,
    val name: String,
    @DrawableRes val imageResId: Int
)
package com.example.mcdonaldsclone.core.database.model

import androidx.annotation.DrawableRes

data class Set(
    val listMenuItems: List<MenuItem>,
    @DrawableRes val imageResId: Int,
    val price: Double,
    val quantity: Int
) {}
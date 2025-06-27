package com.example.mcdonaldsclone.core.database.model

import androidx.annotation.DrawableRes

data class Coupon(
    val id: Long,
    val title: String,
    val category: String,
    val price: Int,
    @DrawableRes val imageResId: Int,
    val isUsed: Boolean = false,
    val availableFrom: String,
    val availableTo: String,
    val set: Set
)
package com.example.mcdonaldsclone.core.database.model

import androidx.annotation.DrawableRes

data class LoyaltyItem(
    val id: Long,
    val title: String,
    @DrawableRes val imageResId: Int,
    val points: Int,
    val set: Set
)

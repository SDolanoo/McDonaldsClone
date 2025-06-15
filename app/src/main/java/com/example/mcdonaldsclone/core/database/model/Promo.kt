package com.example.mcdonaldsclone.core.database.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class Promo(
    val id: Int,
    val title: String,
    val subtitle: String,
    @DrawableRes val imageResId: Int,
    val textColor: Color,
    val textBackgroundColor: Color
)

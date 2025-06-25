package com.example.mcdonaldsclone.core.database.model

import androidx.annotation.DrawableRes
import com.example.mcdonaldsclone.R

data class Set(
    val listMenuItems: List<MenuItem> = emptyList(),
    @DrawableRes val imageResId: Int = R.drawable.ic_launcher_background,
    val price: Double = 0.0,
    val quantity: Int = 1
) {
    companion object {
        val EMPTY = Set()
    }

    val isEmpty: Boolean
        get() = this == EMPTY
}
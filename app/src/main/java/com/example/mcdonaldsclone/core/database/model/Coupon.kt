package com.example.mcdonaldsclone.core.database.model

import java.time.LocalDate

data class Coupon(
    val id: Long,
    val title: String,
    val category: String,
    val price: Int,
    val imageUrl: String,
    val isUsed: Boolean = false,
    val availableFrom: String,
    val availableTo: String
)
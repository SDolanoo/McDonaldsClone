package com.example.mcdonaldsclone.core.database.model

import java.time.LocalDate

data class Coupon(
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String,
    val isUsed: Boolean = false,
    val expirationDate: LocalDate
)
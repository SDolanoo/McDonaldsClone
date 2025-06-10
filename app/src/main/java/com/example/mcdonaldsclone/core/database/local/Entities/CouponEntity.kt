package com.example.mcdonaldsclone.core.database.local.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "coupons")
data class CouponEntity(
    @PrimaryKey val id: Long,
    val title: String,
    val description: String,
    val imageUrl: String,
    val isUsed: Boolean = false,
    val expirationDate: LocalDate
)

package com.example.mcdonaldsclone.core.database.local.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartItemEntity(
    @PrimaryKey val id: Int,
    val productId: Int,
    val quantity: Int
)

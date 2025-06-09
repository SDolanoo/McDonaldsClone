package com.example.mcdonaldsclone.core.database.local.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
    val price: Double,
    val categoryId: Int,
    val isAvailable: Boolean = true
)

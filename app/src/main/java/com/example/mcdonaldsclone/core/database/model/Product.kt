package com.example.mcdonaldsclone.core.database.model

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
    val price: Double,
    val categoryId: Int,
    val isAvailable: Boolean = true
)
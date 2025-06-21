package com.example.mcdonaldsclone.features.menu.model

data class Product(
    val id: Long,
    val name: String,
    val description: String,
    val imageUrl: String,
    val price: Double,
    val categoryId: Int,
    val isAvailable: Boolean = true,
    val subCategoryId: Long?
)
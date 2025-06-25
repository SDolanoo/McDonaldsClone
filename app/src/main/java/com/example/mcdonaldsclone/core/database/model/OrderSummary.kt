package com.example.mcdonaldsclone.core.database.model

import com.example.mcdonaldsclone.core.database.model.archiveModel.CartItem
import java.time.LocalDateTime

data class OrderSummary(
    val id: Int,
    val items: List<CartItem>,
    val totalPrice: Double,
    val timestamp: LocalDateTime
)
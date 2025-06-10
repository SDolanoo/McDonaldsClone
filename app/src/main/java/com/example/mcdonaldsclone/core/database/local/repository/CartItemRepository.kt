package com.example.mcdonaldsclone.core.database.local.repository

import com.example.mcdonaldsclone.core.database.local.Entities.CartItemEntity
import com.example.mcdonaldsclone.core.database.local.dao.CartItemDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartItemRepository @Inject constructor(
    private val cartItemDao: CartItemDao
) {
    fun getAll(): Flow<List<CartItemEntity>> = cartItemDao.getAll()

    suspend fun getByProductId(productId: Long): CartItemEntity? = cartItemDao.getByProductId(productId = productId)

    suspend fun upsert(item: CartItemEntity) = cartItemDao.upsert(item = item)

    suspend fun delete(item: CartItemEntity) = cartItemDao.delete(item = item)

    suspend fun clearCart() = cartItemDao.clearCart()
}
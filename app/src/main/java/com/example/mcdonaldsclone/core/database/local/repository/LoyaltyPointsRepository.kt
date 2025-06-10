package com.example.mcdonaldsclone.core.database.local.repository

import com.example.mcdonaldsclone.core.database.local.Entities.CartItemEntity
import com.example.mcdonaldsclone.core.database.local.Entities.LoyaltyPointsEntity
import com.example.mcdonaldsclone.core.database.local.dao.CartItemDao
import com.example.mcdonaldsclone.core.database.local.dao.LoyaltyPointsDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoyaltyPointsRepository @Inject constructor(
    private val loyaltyPointsDao: LoyaltyPointsDao
) {
    fun get(): Flow<LoyaltyPointsEntity?> = loyaltyPointsDao.get()

    suspend fun set(points: LoyaltyPointsEntity) = loyaltyPointsDao.set(points = points)

    suspend fun updatePoints(delta: Int) {
        """:param delta can be 300, but also -300"""
        loyaltyPointsDao.updatePoints(delta = delta)
    }
}
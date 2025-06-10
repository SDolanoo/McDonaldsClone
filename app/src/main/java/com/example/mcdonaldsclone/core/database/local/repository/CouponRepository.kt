package com.example.mcdonaldsclone.core.database.local.repository

import android.R.attr.category
import com.example.mcdonaldsclone.core.database.fakeData.FakeDataProvider.categories
import com.example.mcdonaldsclone.core.database.local.Entities.CategoryEntity
import com.example.mcdonaldsclone.core.database.local.Entities.CouponEntity
import com.example.mcdonaldsclone.core.database.local.dao.CategoryDao
import com.example.mcdonaldsclone.core.database.local.dao.CouponDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CouponRepository @Inject constructor(
    private val couponDao: CouponDao
) {
    fun getAll(): Flow<List<CouponEntity>> = couponDao.getAll()

    fun getActive(today: String): Flow<List<CouponEntity>> = couponDao.getActive(today = today)

    suspend fun markAsUsed(id: Long) = couponDao.markAsUsed(id = id)

    suspend fun insertAll(coupons: List<CouponEntity>) = couponDao.insertAll(coupons = coupons)

    suspend fun insert(coupon: CouponEntity) = couponDao.insert(coupon = coupon)

    suspend fun delete(coupon: CouponEntity) = couponDao.delete(coupon = coupon)
}
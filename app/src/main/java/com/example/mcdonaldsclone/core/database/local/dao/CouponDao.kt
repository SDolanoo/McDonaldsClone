package com.example.mcdonaldsclone.core.database.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import com.example.mcdonaldsclone.core.database.local.Entities.CouponEntity
import java.time.LocalDate

@Dao
interface CouponDao {

    @Query("SELECT * FROM coupons")
    fun getAll(): Flow<List<CouponEntity>>

    @Query("SELECT * FROM coupons WHERE isUsed = 0 AND expirationDate >= :today")
    fun getActive(today: String = LocalDate.now().toString()): Flow<List<CouponEntity>>

    @Query("UPDATE coupons SET isUsed = 1 WHERE id = :id")
    suspend fun markAsUsed(id: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(coupons: List<CouponEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(coupon: CouponEntity)

    @Delete
    suspend fun delete(coupon: CouponEntity)
}

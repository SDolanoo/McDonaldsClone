package com.example.mcdonaldsclone.core.database.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import com.example.mcdonaldsclone.core.database.local.Entities.LoyaltyPointsEntity

@Dao
interface LoyaltyPointsDao {

    @Query("SELECT * FROM loyalty_points WHERE id = 0")
    fun get(): Flow<LoyaltyPointsEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun set(points: LoyaltyPointsEntity)

    @Query("UPDATE loyalty_points SET currentPoints = currentPoints + :delta WHERE id = 0")
    suspend fun updatePoints(delta: Int)
}

package com.example.mcdonaldsclone.core.database.local.repository

import com.example.mcdonaldsclone.core.database.fakeData.FakeDataProvider.products
import com.example.mcdonaldsclone.core.database.local.Entities.ProductEntity
import com.example.mcdonaldsclone.core.database.local.Entities.UserSettingsEntity
import com.example.mcdonaldsclone.core.database.local.dao.ProductDao
import com.example.mcdonaldsclone.core.database.local.dao.UserSettingsDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserSettingsRepostiory @Inject constructor(
    private val userSettingsDao: UserSettingsDao
) {
    fun get(): Flow<UserSettingsEntity?> = userSettingsDao.get()

    suspend fun set(settings: UserSettingsEntity) = userSettingsDao.set(settings = settings)
}
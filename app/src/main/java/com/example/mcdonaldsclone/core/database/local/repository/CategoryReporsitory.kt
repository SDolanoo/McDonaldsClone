package com.example.mcdonaldsclone.core.database.local.repository

import com.example.mcdonaldsclone.core.database.local.Entities.CategoryEntity
import com.example.mcdonaldsclone.core.database.local.dao.CategoryDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryReporsitory @Inject constructor(
    private val categoryDao: CategoryDao
) {
    fun getAll(): Flow<List<CategoryEntity>> = categoryDao.getAll()

    suspend fun insertAll(categories: List<CategoryEntity>) = categoryDao.insertAll(categories = categories)

    suspend fun insert(category: CategoryEntity) = categoryDao.insert(category = category)

    suspend fun delete(category: CategoryEntity) = categoryDao.delete(category = category)
}
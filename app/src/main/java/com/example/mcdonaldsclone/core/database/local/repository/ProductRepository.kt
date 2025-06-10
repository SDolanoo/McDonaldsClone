package com.example.mcdonaldsclone.core.database.local.repository

import com.example.mcdonaldsclone.core.database.local.Entities.ProductEntity
import com.example.mcdonaldsclone.core.database.local.dao.ProductDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productDao: ProductDao
) {
    fun getAll(): Flow<List<ProductEntity>> = productDao.getAll()

    fun getByCategory(categoryId: Long): Flow<List<ProductEntity>> = productDao.getByCategory(categoryId = categoryId)

    suspend fun getById(id: Long): ProductEntity? = productDao.getById(id = id)

    suspend fun insertAll(products: List<ProductEntity>) = productDao.insertAll(products = products)

    suspend fun insert(product: ProductEntity) = productDao.insert(product = product)

    suspend fun delete(product: ProductEntity) = productDao.delete(product = product)
}
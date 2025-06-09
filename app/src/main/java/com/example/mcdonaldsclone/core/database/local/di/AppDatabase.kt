package com.example.mcdonaldsclone.core.database.local.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.mcdonaldsclone.core.database.local.Entities.CartItemEntity
import com.example.mcdonaldsclone.core.database.local.Entities.CategoryEntity
import com.example.mcdonaldsclone.core.database.local.Entities.CouponEntity
import com.example.mcdonaldsclone.core.database.local.Entities.LoyaltyPointsEntity
import com.example.mcdonaldsclone.core.database.local.Entities.ProductEntity
import com.example.mcdonaldsclone.core.database.local.Entities.UserSettingsEntity
import com.example.mcdonaldsclone.core.database.local.dao.CartItemDao
import com.example.mcdonaldsclone.core.database.local.dao.CategoryDao
import com.example.mcdonaldsclone.core.database.local.dao.CouponDao
import com.example.mcdonaldsclone.core.database.local.dao.LoyaltyPointsDao
import com.example.mcdonaldsclone.core.database.local.dao.ProductDao
import com.example.mcdonaldsclone.core.database.local.dao.UserSettingsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.time.LocalDate
import javax.inject.Singleton


@Database(
    entities = [
        ProductEntity::class, CouponEntity::class, CategoryEntity::class, CartItemEntity::class,
        LoyaltyPointsEntity::class, UserSettingsEntity::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun couponDao(): CouponDao
    abstract fun categoryDao(): CategoryDao
    abstract fun cartItemDao(): CartItemDao
    abstract fun loyaltyPointsDao(): LoyaltyPointsDao
    abstract fun userSettingsDao(): UserSettingsDao
}


class Converters {
    @TypeConverter
    fun fromLocalDate(value: LocalDate): String = value.toString()

    @TypeConverter
    fun toLocalDate(value: String): LocalDate = LocalDate.parse(value)
}

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context):
            AppDatabase {
        return Room.databaseBuilder(
            context = context,
            AppDatabase::class.java,
            "mcd_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideProductDao(appDatabase: AppDatabase): ProductDao {
        return appDatabase.productDao()
    }

    @Provides
    fun provideCouponDao(appDatabase: AppDatabase): CouponDao {
        return appDatabase.couponDao()
    }

    @Provides
    fun provideCategoryDao(appDatabase: AppDatabase): CategoryDao {
        return appDatabase.categoryDao()
    }

    @Provides
    fun provideCartItemDao(appDatabase: AppDatabase): CartItemDao {
        return appDatabase.cartItemDao()
    }

    @Provides
    fun provideLoyaltyPointsDao(appDatabase: AppDatabase): LoyaltyPointsDao {
        return appDatabase.loyaltyPointsDao()
    }

    @Provides
    fun provideUserSettingsDao(appDatabase: AppDatabase): UserSettingsDao {
        return appDatabase.userSettingsDao()
    }
}
package com.example.mcdonaldsclone.core.daggerHilt

import android.app.Application
import android.content.Context
import com.example.mcdonaldsclone.core.database.fakeData.FakeDataProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@HiltAndroidApp
class AppApplication: Application() {}

@Module
@InstallIn(SingletonComponent::class)
object DIAppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideFakeDataProvider(): FakeDataProvider {
        return FakeDataProvider
    }
}
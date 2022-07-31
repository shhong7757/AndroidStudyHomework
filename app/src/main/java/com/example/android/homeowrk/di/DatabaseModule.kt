package com.example.android.homeowrk.di

import android.content.Context
import com.example.android.homeowrk.data.AppDatabase
import com.example.android.homeowrk.data.FlowerDao
import com.example.android.homeowrk.data.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Provides
    fun provideFlowerDao(appDatabase: AppDatabase): FlowerDao {
        return appDatabase.flowerDao()
    }
}
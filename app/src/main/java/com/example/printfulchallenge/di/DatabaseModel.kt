package com.example.printfulchallenge.di

import android.content.Context
import com.example.printfulchallenge.database.AppDatabase
import com.example.printfulchallenge.database.dao.CatBreedDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModel {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase = AppDatabase.getInstance(context)

    @Provides
    fun provideCatDao(appDatabase: AppDatabase): CatBreedDao = appDatabase.catBreedDao()

}
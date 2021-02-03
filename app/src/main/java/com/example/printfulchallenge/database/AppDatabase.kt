package com.example.printfulchallenge.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.printfulchallenge.database.dao.CatBreedDao
import com.example.printfulchallenge.database.model.DbCatBreed
import com.example.printfulchallenge.utils.DATABASE_NAME

/**
 * SQLite Database for storing the CatAPI results
 */
@Database(entities = [DbCatBreed::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun catBreedDao(): CatBreedDao

    companion object {

        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .build()
        }
    }
}
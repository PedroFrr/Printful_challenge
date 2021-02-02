package com.example.printfulchallenge.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.printfulchallenge.database.dao.CatDao
import com.example.printfulchallenge.database.model.DbCat
import com.example.printfulchallenge.utils.DATABASE_NAME

/**
 * SQLite Database for storing the CatAPI results
 */
@Database(entities = [DbCat::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun catDao(): CatDao

    companion object {

        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        //Adding callback to pre populate the database with the city list from JSON
        //For a better solution this city list should be hosted in a remote server so I wouldn't have to do this
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
//                            val request = OneTimeWorkRequestBuilder<CitiesDatabaseWorker>().build()
//                            WorkManager.getInstance(context).enqueue(request)
                        }
                    }
                )
                .build()
        }
    }
}
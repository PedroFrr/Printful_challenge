package com.example.printfulchallenge.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.printfulchallenge.database.model.DbCatBreed

@Dao
interface CatBreedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(catBreeds: List<DbCatBreed>)

    @Query("SELECT * FROM catBreed WHERE " +
            "name LIKE :queryString " +
            "ORDER BY name ASC")
    fun breedsByName(queryString: String): PagingSource<Int, DbCatBreed>

    @Query("DELETE FROM catBreed")
    suspend fun clearCatBreeds()

    @Query("SELECT * FROM catBreed WHERE id = :catBreedId")
    suspend fun fetchCatBreedDetails(catBreedId: String): DbCatBreed

}
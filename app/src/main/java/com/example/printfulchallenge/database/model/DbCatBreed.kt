package com.example.printfulchallenge.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "catBreed")
data class DbCatBreed(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val name: String,
    val referenceImageId: String,
    val weight: String,
    val originCountry: String,
    val description: String,
    val affectionLevel: Int

)
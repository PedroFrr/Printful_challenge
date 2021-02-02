package com.example.printfulchallenge.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

//TODO add proprieties
@Entity(tableName = "cat")
data class DbCat(
    @PrimaryKey val id: String = UUID.randomUUID().toString()
) {
}
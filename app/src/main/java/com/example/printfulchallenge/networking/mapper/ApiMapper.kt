package com.example.printfulchallenge.networking.mapper

import com.example.printfulchallenge.database.model.DbCatBreed
import com.example.printfulchallenge.networking.response.CatBreedResponse

interface ApiMapper {

    fun mapApiCatBreedToModel(apiCatBreed: CatBreedResponse): DbCatBreed

}
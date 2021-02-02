package com.example.printfulchallenge.networking.mapper

import com.example.printfulchallenge.database.model.DbCatBreed
import com.example.printfulchallenge.networking.response.CatBreedResponse
import javax.inject.Inject

class ApiMapperImpl @Inject constructor() :ApiMapper {

    override fun mapApiCatBreedToModel(apiCatBreed: CatBreedResponse): DbCatBreed = with(apiCatBreed) {
        DbCatBreed(
            id = id,
            name = name,
            referenceImageId = referenceImageId ?: "",
            weight = weight.metric,
            originCountry = originCountry,
            description = description,
            affectionLevel = affectionLevel
        )
    }
}
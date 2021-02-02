package com.example.printfulchallenge.database.model

import com.example.printfulchallenge.networking.response.CatBreedResponse

sealed class CatBreedUiModel {

    data class CatBreedItem(val catBreed: CatBreedResponse) : CatBreedUiModel() {
        val startingLetter: String
            get() = this.catBreed.name.first().toString()
    }
    data class SeparatorItem(val description: String) : CatBreedUiModel()


}

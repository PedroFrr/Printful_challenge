package com.example.printfulchallenge.networking.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
* Immutable model class for a cat breed.
* Objects of this type are received from the Cat API, therefore all the fields are annotated Json .
*/
@JsonClass(generateAdapter = true)
data class CatBreedResponse(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "reference_image_id") val referenceImageId: String?,
    @Json(name="weight") val weight: WeightResponse,
    @Json(name="origin") val originCountry: String,
    @Json(name="description") val description: String,
    @Json(name="affection_level") val affectionLevel: Int,

)
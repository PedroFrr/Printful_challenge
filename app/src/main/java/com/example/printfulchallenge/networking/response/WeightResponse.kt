package com.example.printfulchallenge.networking.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeightResponse(
    @Json(name = "metric") val metric: String
)

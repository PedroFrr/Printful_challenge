package com.example.printfulchallenge.networking

import javax.inject.Inject

class CatApiClient @Inject constructor(
    private val catApiService: CatApiService
)

//TODO add result handling over Service
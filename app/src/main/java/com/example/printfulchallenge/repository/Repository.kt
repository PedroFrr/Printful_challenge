package com.example.printfulchallenge.repository

import androidx.paging.PagingData
import com.example.printfulchallenge.networking.response.CatBreedResponse
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun fetchCatBreeds(): Flow<PagingData<CatBreedResponse>>
}
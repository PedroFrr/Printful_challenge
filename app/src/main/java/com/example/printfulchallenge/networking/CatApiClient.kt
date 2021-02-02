package com.example.printfulchallenge.networking

import com.example.printfulchallenge.database.model.Failure
import com.example.printfulchallenge.database.model.Result
import com.example.printfulchallenge.database.model.Success
import com.example.printfulchallenge.networking.response.CatBreedResponse
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CatApiClient @Inject constructor(
    private val catApiService: CatApiService
) {

    suspend fun fetchCatBreeds(
        page: Int,
        itemsPerPage: Int
    ): Result<List<CatBreedResponse>> =
        try {
            val response = catApiService.fetchCatBreeds()
            Success(response)
        } catch (exception: IOException) {
            Failure(exception)
        } catch (exception: HttpException) {
            Failure(exception)
        }
}


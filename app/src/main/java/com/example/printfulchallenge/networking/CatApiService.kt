package com.example.printfulchallenge.networking

import com.example.printfulchallenge.networking.response.CatBreedResponse
import com.example.printfulchallenge.utils.CAT_BREEDS_PAGE_SIZE
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Cat API communication setup via Retrofit.
 */
interface CatApiService {

    @GET("breeds")
    suspend fun fetchCatBreeds(
        @Query("page") page: Int? = null,
        @Query("limit") itemsPerPage: Int = CAT_BREEDS_PAGE_SIZE, //default number of items to retrieve per page
        @Query("order") order: String = "asc" //Default order for displaying breeds (can be ASC or DESC)
    ): List<CatBreedResponse>
}
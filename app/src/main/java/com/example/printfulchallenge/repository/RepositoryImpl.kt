package com.example.printfulchallenge.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.printfulchallenge.database.dao.CatBreedDao
import com.example.printfulchallenge.database.model.CatBreedPagingSource
import com.example.printfulchallenge.database.model.DbCatBreed
import com.example.printfulchallenge.networking.CatApiService
import com.example.printfulchallenge.networking.mapper.ApiMapper
import com.example.printfulchallenge.networking.response.CatBreedResponse
import com.example.printfulchallenge.utils.CAT_BREEDS_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val catApiService: CatApiService,
    private val apiMapper: ApiMapper,
    private val catBreedDao: CatBreedDao
) : Repository {

    override fun fetchCatBreeds(): Flow<PagingData<CatBreedResponse>> {
        return Pager(
            config = PagingConfig(
                pageSize = CAT_BREEDS_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CatBreedPagingSource(catApiService, apiMapper, catBreedDao) }
        ).flow
    }

    override suspend fun fetchCatBreedDetails(catBreedId: String): DbCatBreed = catBreedDao.fetchCatBreedDetails(catBreedId)


}
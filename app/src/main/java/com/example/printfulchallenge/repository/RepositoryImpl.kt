package com.example.printfulchallenge.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.printfulchallenge.database.model.DbCatBreed
import com.example.printfulchallenge.database.model.Failure
import com.example.printfulchallenge.database.model.Loading
import com.example.printfulchallenge.database.model.Result
import com.example.printfulchallenge.database.model.Success
import com.example.printfulchallenge.networking.CatApiClient
import com.example.printfulchallenge.networking.CatApiService
import com.example.printfulchallenge.networking.mapper.ApiMapper
import com.example.printfulchallenge.networking.response.CatBreedResponse
import com.example.printfulchallenge.ui.cats.list.CatBreedPagingSource
import com.example.printfulchallenge.utils.CAT_BREEDS_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val catApiService: CatApiService,
    private val apiMapper: ApiMapper
) : Repository {

//    override suspend fun fetchCatBreeds(
//        page: Int,
//        itemsPerPage: Int
//    ): Flow<Result<List<DbCatBreed>>> {
//        return flow {
//            emit(Loading)
//            val results = catApiClient.fetchCatBreeds(page = page, itemsPerPage = itemsPerPage)
//            if(results is Success){
//                val catBreeds = results.data.map { apiMapper.mapApiCatBreedToModel(it) }
//                emit(Success(catBreeds))
//            } else if (results is Failure) {
//                emit(Failure(results.error))
//            }
//        }
//    }

    override fun fetchCatBreeds(): Flow<PagingData<CatBreedResponse>> {
        return Pager(
            config = PagingConfig(
                pageSize = CAT_BREEDS_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CatBreedPagingSource(catApiService) }
        ).flow
    }


}
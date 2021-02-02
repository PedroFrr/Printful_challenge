package com.example.printfulchallenge.ui.cats.list

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.printfulchallenge.networking.CatApiService
import com.example.printfulchallenge.networking.response.CatBreedResponse
import com.example.printfulchallenge.utils.CAT_API_STARTING_PAGE_INDEX
import com.example.printfulchallenge.utils.CAT_BREEDS_PAGE_SIZE
import retrofit2.HttpException
import java.io.IOException

//TODO change class package
class CatBreedPagingSource(
    private val catApiService: CatApiService
) : PagingSource<Int, CatBreedResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CatBreedResponse> {
        val position = params.key ?: CAT_API_STARTING_PAGE_INDEX
        return try {
            val response = catApiService.fetchCatBreeds(page = position, itemsPerPage = params.loadSize)
            val nextKey = if (response.isEmpty()) {
                null
            } else {
                // initial load size = 3 * NETWORK_PAGE_SIZE
                // ensure we're not requesting duplicating items, at the 2nd request
                position + (params.loadSize / CAT_BREEDS_PAGE_SIZE)
            }
            LoadResult.Page(
                data = response,
                prevKey = if (position == CAT_API_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    //TODO change
    override fun getRefreshKey(state: PagingState<Int, CatBreedResponse>): Int? = null

}

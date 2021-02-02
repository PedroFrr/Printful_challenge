package com.example.printfulchallenge.ui.cats.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.printfulchallenge.database.model.DbCatBreed
import com.example.printfulchallenge.networking.response.CatBreedResponse
import com.example.printfulchallenge.repository.Repository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow

class CatBreedListViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

    val _catBreeds = MutableLiveData<List<DbCatBreed>>()
    fun getCatBreeds(): LiveData<List<DbCatBreed>> = _catBreeds

    private var currentQueryValue: String? = null

    private var currentSearchResult: Flow<PagingData<CatBreedResponse>>? = null

    fun fetchCatBreeds(queryString: String): Flow<PagingData<CatBreedResponse>> {
        val lastResult = currentSearchResult
        if (queryString == currentQueryValue && lastResult != null) {
            return lastResult
        }
        currentQueryValue = queryString
        val newResult: Flow<PagingData<CatBreedResponse>> = repository.fetchCatBreeds()
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }


}
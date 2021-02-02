package com.example.printfulchallenge.ui.cats.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.printfulchallenge.database.model.DbCatBreed
import com.example.printfulchallenge.repository.Repository
import kotlinx.coroutines.launch

class CatBreedDetailViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _catBreedDetail = MutableLiveData<DbCatBreed>()
    fun getCatBreedDetails(): LiveData<DbCatBreed> = _catBreedDetail

    fun fetchCatBreedDetails(catBreedId: String) {
        viewModelScope.launch {
            val catBreedDetail = repository.fetchCatBreedDetails(catBreedId)

            _catBreedDetail.postValue(catBreedDetail)
        }

    }

}
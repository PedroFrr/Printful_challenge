package com.example.printfulchallenge.ui.cats.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import com.example.printfulchallenge.database.model.CatBreedUiModel
import com.example.printfulchallenge.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CatBreedListViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

    /**
     * Returns a Flow of PagingData of type CatBreedUiModel
     * This model is created in order to add separators on the list itself
     */
    fun fetchCatBreeds(): Flow<PagingData<CatBreedUiModel>> {

        val newResult: Flow<PagingData<CatBreedUiModel>> = repository.fetchCatBreeds()
            .map { pagingData -> pagingData.map { CatBreedUiModel.CatBreedItem(it) } }
            .map {
                it.insertSeparators<CatBreedUiModel.CatBreedItem, CatBreedUiModel> { before, after ->
                    if (after == null) {
                        // we're at the end of the list
                        return@insertSeparators null
                    }

                    if (before == null) {
                        // we're at the beginning of the list
                        return@insertSeparators CatBreedUiModel.SeparatorItem(after.startingLetter)
                    }

                    // check between 2 items
                    if (before.startingLetter < after.startingLetter) {

                        CatBreedUiModel.SeparatorItem(after.startingLetter)

                    } else {
                        // no separator
                        null
                    }

                }
            }
            .cachedIn(viewModelScope)

        return newResult
    }


}
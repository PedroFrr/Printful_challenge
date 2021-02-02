package com.example.printfulchallenge.ui.cats.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.printfulchallenge.R
import com.example.printfulchallenge.database.model.Failure
import com.example.printfulchallenge.database.model.Loading
import com.example.printfulchallenge.database.model.Success
import com.example.printfulchallenge.databinding.FragmentCatBreedListBinding
import com.example.printfulchallenge.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CatBreedListFragment : Fragment(R.layout.fragment_cat_breed_list) {

    private val binding by viewBinding(FragmentCatBreedListBinding::bind)
    private val catBreedListAdapter by lazy { CatBreedListAdapter() }
    private val catBreedListViewModel by viewModels<CatBreedListViewModel>()
    private var searchJob: Job? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        initObservables()

        val query = savedInstanceState?.getString(LAST_SEARCH_QUERY) ?: DEFAULT_QUERY
        search(query)
        initSearch(query)
//        binding.retryButton.setOnClickListener { adapter.retry() }
    }

    private fun initUi(){

        binding.catBreedsRecyclerView.apply {
            adapter = catBreedListAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            hasFixedSize()
        }


    }

    private fun initObservables(){
//        //TODO change once search is implemented
//        lifecycleScope.launch {
//            catBreedListViewModel.fetchCatBreeds().observe(viewLifecycleOwner, { result ->
//                when(result){
//                    //TODO handle Failure and Loading
//                    is Success -> catBreedListAdapter.submitList(result.data)
//                    is Failure -> null
//                    Loading -> null
//                }
//
//            })
//        }

    }

    private fun initSearch(query: String) {

        /*KTX Core addTextChangedListener
        * Once search changes (and after debounce period) display new data
         */

        //TODO refactor as we're setting the text manually it might not trigger the function call
        binding.searchEditText.doOnTextChanged { _, _, _, _ ->
            updateRepoListFromInput()
        }

        binding.searchEditText.setText(query)

        // Scroll to top when the list is refreshed from network.
        lifecycleScope.launch {
            catBreedListAdapter.loadStateFlow
                // Only emit when REFRESH LoadState for RemoteMediator changes.
                .distinctUntilChangedBy { it.refresh }
                // Only react to cases where Remote REFRESH completes i.e., NotLoading.
                .filter { it.refresh is LoadState.NotLoading }
                .collect { binding.catBreedsRecyclerView.scrollToPosition(0) }
        }
    }

    private fun updateRepoListFromInput() {
        binding.searchEditText.text.trim().let {
            if (it.isNotEmpty()) {
                search(it.toString())
            }
        }
    }

    private fun search(query: String) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            catBreedListViewModel.fetchCatBreeds(query).collectLatest {
                catBreedListAdapter.submitData(it)
            }
        }
    }

    companion object {
        private const val LAST_SEARCH_QUERY: String = "last_search_query"
        private const val DEFAULT_QUERY = ""
    }



}
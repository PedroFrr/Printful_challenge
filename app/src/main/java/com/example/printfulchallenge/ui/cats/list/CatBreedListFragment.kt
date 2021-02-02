package com.example.printfulchallenge.ui.cats.list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.printfulchallenge.R
import com.example.printfulchallenge.databinding.FragmentCatBreedListBinding
import com.example.printfulchallenge.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        fetchCatBreeds()
        initSearch()
    }

    private fun initUi() {

        binding.retryButton.setOnClickListener { catBreedListAdapter.retry() }

        initAdapter()

    }

    private fun initAdapter() {
        binding.catBreedsRecyclerView.apply {
            adapter = catBreedListAdapter.withLoadStateHeaderAndFooter(
                header = CatBreedsLoadStateAdapter { catBreedListAdapter.retry() },
                footer = CatBreedsLoadStateAdapter { catBreedListAdapter.retry() }
            )
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            hasFixedSize()

        }

        /*
        Listener for changes on Paging Status - Loading, error, success
        Updates the Fragment according to the status (should show retry button, cat breed list or loading progress bar)
         */
        catBreedListAdapter.addLoadStateListener { loadState ->
            // Only show the list if refresh succeeds.
            binding.catBreedsRecyclerView.isVisible =
                loadState.source.refresh is LoadState.NotLoading
            // Show loading spinner during initial load or refresh.
            binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
            // Show the retry state if initial load or refresh fails.
            binding.retryButton.isVisible = loadState.source.refresh is LoadState.Error

            // Toast on any error, regardless of whether it came from RemoteMediator or PagingSource
            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error
            errorState?.let {
                Toast.makeText(
                    requireContext(),
                    "\uD83D\uDE28 Wooops ${it.error}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }


    }

    private fun initSearch() {

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


    private fun fetchCatBreeds() {
        lifecycleScope.launch {
            catBreedListViewModel.fetchCatBreeds().collectLatest {
                catBreedListAdapter.submitData(it)
            }
        }
    }


}
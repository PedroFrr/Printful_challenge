package com.example.printfulchallenge.ui.cats.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.printfulchallenge.R
import com.example.printfulchallenge.databinding.FragmentCatBreedDetailBinding
import com.example.printfulchallenge.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 */

@AndroidEntryPoint
class CatBreedDetailFragment : Fragment(R.layout.fragment_cat_breed_detail) {

    private val binding by viewBinding(FragmentCatBreedDetailBinding::bind)
    private val catBreedDetailViewModel by viewModels<CatBreedDetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        initObservables()
    }

    private fun initUi(){
        arguments?.let {
            val args = CatBreedDetailFragmentArgs.fromBundle(it)
            val catBreedId = args.catBreedId
            catBreedDetailViewModel.fetchCatBreedDetails(catBreedId)

        }
    }

    private fun initObservables(){
        catBreedDetailViewModel.getCatBreedDetails().observe(viewLifecycleOwner, { catBreedDetail ->
            binding.apply {
                breedName.text = catBreedDetail.name
            }
        })
    }

}
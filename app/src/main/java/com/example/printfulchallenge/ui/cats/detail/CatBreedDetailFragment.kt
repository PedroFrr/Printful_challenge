package com.example.printfulchallenge.ui.cats.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.printfulchallenge.R
import com.example.printfulchallenge.databinding.FragmentCatBreedDetailBinding
import com.example.printfulchallenge.utils.CAT_IMAGES_BASE_URL
import com.example.printfulchallenge.utils.loadImage
import com.example.printfulchallenge.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass to display the info about a cat breed.
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
        /*
         Argument passed with Jetpack navigation
         Once we retrieve the catBreedId a call is made to the Database to retrieve its info
         */

        arguments?.let {
            val args = CatBreedDetailFragmentArgs.fromBundle(it)
            val catBreedId = args.catBreedId
            catBreedDetailViewModel.fetchCatBreedDetails(catBreedId)

        }
    }

    private fun initObservables(){
        catBreedDetailViewModel.getCatBreedDetails().observe(viewLifecycleOwner, { catBreedDetail ->
            binding.apply {
                catBreedName.text = catBreedDetail.name
                catBreedDetailImageView.loadImage(catBreedDetail.referenceImageId.let{"$CAT_IMAGES_BASE_URL$it.jpg"}, R.drawable.ic_baseline_adb_24)
                catBreedDescription.text = catBreedDetail.description
                catBreedAverageWeight.text = catBreedDetail.weight
                catBreedOriginCountry.text = catBreedDetail.originCountry
                catBreedAffectionLevel.rating = catBreedDetail.affectionLevel.toFloat()
            }
        })
    }

}
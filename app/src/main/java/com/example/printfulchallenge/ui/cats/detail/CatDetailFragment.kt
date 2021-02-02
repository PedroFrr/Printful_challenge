package com.example.printfulchallenge.ui.cats.detail

import androidx.fragment.app.Fragment
import com.example.printfulchallenge.R
import com.example.printfulchallenge.databinding.FragmentCatBreedListBinding
import com.example.printfulchallenge.databinding.FragmentCatDetailBinding
import com.example.printfulchallenge.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 */

@AndroidEntryPoint
class CatDetailFragment : Fragment(R.layout.fragment_cat_detail) {

    private val binding by viewBinding(FragmentCatDetailBinding::bind)

}
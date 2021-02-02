package com.example.printfulchallenge.ui.cats.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.printfulchallenge.R
import com.example.printfulchallenge.databinding.CatBreedsLoadStateFooterViewItemBinding

class CatBreedsLoadStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<CatBreedsLoadStateAdapter.CatBreedsLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: CatBreedsLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): CatBreedsLoadStateViewHolder {
        return CatBreedsLoadStateViewHolder.create(parent, retry)
    }

    class CatBreedsLoadStateViewHolder(
        private val binding: CatBreedsLoadStateFooterViewItemBinding,
        retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.retryButton.setOnClickListener { retry.invoke() }
        }

        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                binding.errorMsg.text = loadState.error.localizedMessage
            }
            binding.progressBar.isVisible = loadState is LoadState.Loading
            binding.retryButton.isVisible = loadState !is LoadState.Loading
            binding.errorMsg.isVisible = loadState !is LoadState.Loading
        }

        companion object {
            fun create(parent: ViewGroup, retry: () -> Unit): CatBreedsLoadStateViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.cat_breeds_load_state_footer_view_item, parent, false)
                val binding = CatBreedsLoadStateFooterViewItemBinding.bind(view)
                return CatBreedsLoadStateViewHolder(binding, retry)
            }
        }
    }
}

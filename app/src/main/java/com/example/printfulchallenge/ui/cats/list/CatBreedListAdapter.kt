package com.example.printfulchallenge.ui.cats.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.printfulchallenge.databinding.ListItemCatBreedBinding
import com.example.printfulchallenge.networking.response.CatBreedResponse

class CatBreedListAdapter :
    PagingDataAdapter<CatBreedResponse, CatBreedListAdapter.ViewHolder>(CatBreedListDiffCallBack()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(
        private val binding: ListItemCatBreedBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemCatBreedBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

        fun bind(item: CatBreedResponse) {
            with(binding) {
                catBreedItem.setOnClickListener {
                    //TODO move to detail page
                }
                catBreedName.text = item.name
            }
        }

    }

}


private class CatBreedListDiffCallBack : DiffUtil.ItemCallback<CatBreedResponse>() {
    override fun areContentsTheSame(oldItem: CatBreedResponse, newItem: CatBreedResponse): Boolean = oldItem.id == newItem.id

    override fun areItemsTheSame(oldItem: CatBreedResponse, newItem: CatBreedResponse): Boolean = oldItem == newItem
}
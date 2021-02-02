package com.example.printfulchallenge.ui.cats.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.printfulchallenge.R
import com.example.printfulchallenge.database.model.CatBreedUiModel
import com.example.printfulchallenge.databinding.ListItemCatBreedBinding
import com.example.printfulchallenge.networking.response.CatBreedResponse
import com.example.printfulchallenge.utils.CAT_IMAGES_BASE_URL
import com.example.printfulchallenge.utils.loadImage

/**
 * Paginated Adapter for cat breed list
 * Returns data from the API in a paginated way to avoid making unnecessary calls
 * The [CatBreedUiModel] is used so the PaginatedAdapter accepts both headers and items.
 */
class CatBreedListAdapter :
    PagingDataAdapter<CatBreedUiModel, RecyclerView.ViewHolder>(CatBreedUiModelListDiffCallBack()) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val catBreedUiModel = getItem(position)
        catBreedUiModel.let {
            when (it) {
                is CatBreedUiModel.CatBreedItem -> (holder as CatBreedViewHolder).bind(it.catBreed)
                is CatBreedUiModel.SeparatorItem -> (holder as SeparatorViewHolder).bind(it.description)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == R.layout.list_item_cat_breed) {
            CatBreedViewHolder.from(parent)
        } else {
            SeparatorViewHolder.from(parent)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is CatBreedUiModel.CatBreedItem -> R.layout.list_item_cat_breed
            is CatBreedUiModel.SeparatorItem -> R.layout.separator_view_cat_breed_item
            null -> throw UnsupportedOperationException("Unknown view")
        }
    }



    class CatBreedViewHolder private constructor(
        private val binding: ListItemCatBreedBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): CatBreedViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemCatBreedBinding.inflate(layoutInflater, parent, false)
                return CatBreedViewHolder(binding)
            }
        }

        fun bind(item: CatBreedResponse) {
            with(binding) {
                catBreedName.text = item.name
                catBreedImage.loadImage(item.referenceImageId.let{"$CAT_IMAGES_BASE_URL$it.jpg"}, R.drawable.ic_baseline_adb_24)
                catBreedContainer.setOnClickListener {
                    val direction = CatBreedListFragmentDirections.catBreedListToDetail(item.id)
                    Navigation.findNavController(it).navigate(direction)
                }
            }
        }

    }

    class SeparatorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val description: TextView = view.findViewById(R.id.separator_description)

        fun bind(separatorText: String) {
            description.text = separatorText
        }

        companion object {
            fun from(parent: ViewGroup): SeparatorViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.separator_view_cat_breed_item, parent, false)
                return SeparatorViewHolder(view)
            }
        }
    }

}


private class CatBreedUiModelListDiffCallBack : DiffUtil.ItemCallback<CatBreedUiModel>() {

    override fun areItemsTheSame(oldItem: CatBreedUiModel, newItem: CatBreedUiModel): Boolean {
        return (oldItem is CatBreedUiModel.CatBreedItem && newItem is CatBreedUiModel.CatBreedItem &&
                oldItem.catBreed.id == newItem.catBreed.id) ||
                (oldItem is CatBreedUiModel.SeparatorItem && newItem is CatBreedUiModel.SeparatorItem &&
                        oldItem.description == newItem.description)
    }


    override fun areContentsTheSame(oldItem: CatBreedUiModel, newItem: CatBreedUiModel): Boolean =
        oldItem == newItem
}
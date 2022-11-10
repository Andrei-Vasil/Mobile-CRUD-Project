package com.internship.crudapp.feature.main_screen

import androidx.recyclerview.widget.RecyclerView
import com.internship.crudapp.R
import com.internship.crudapp.databinding.CatFeedCardBinding
import com.internship.crudapp.feature.main_screen.dtos.Cat
import java.util.UUID

class CatViewHolder(
    private val binding: CatFeedCardBinding,
    private val onButtonClickItemListener: OnButtonClickItemListener
) : RecyclerView.ViewHolder(binding.root) {

    private var itemId: UUID? = null

    init {
        binding.updateCatBtn.setOnClickListener {
            itemId?.let { itemId -> onButtonClickItemListener(Pair(itemId, UPDATE)) }
        }

        binding.deleteCatBtn.setOnClickListener {
            itemId?.let { itemId -> onButtonClickItemListener(Pair(itemId, DELETE)) }
        }
    }

    fun bind(cat: Cat) {
        itemId = cat.id
        binding.catName.text = binding.root.context.getString(R.string.cat_name_fmts, cat.name)
        binding.catAge.text = binding.root.context.getString(R.string.age_fmts, cat.age.toString())
        binding.catBreed.text = binding.root.context.getString(R.string.breed_fmts, cat.breed)
        binding.catColor.text = binding.root.context.getString(R.string.owner_name_fmts, cat.color)
        binding.catOwner.text = binding.root.context.getString(R.string.color_fmts, cat.owner)
    }

    companion object {
        const val UPDATE = 1
        const val DELETE = 0
    }
}

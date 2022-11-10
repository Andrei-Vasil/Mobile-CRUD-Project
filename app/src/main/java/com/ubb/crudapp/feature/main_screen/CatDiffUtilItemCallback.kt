package com.ubb.crudapp.feature.main_screen

import androidx.recyclerview.widget.DiffUtil
import com.ubb.crudapp.feature.main_screen.dtos.Cat

class CatDiffUtilItemCallback : DiffUtil.ItemCallback<Cat>() {
    override fun areItemsTheSame(oldItem: Cat, newItem: Cat) = oldItem == newItem

    override fun areContentsTheSame(oldItem: Cat, newItem: Cat) = oldItem == newItem

}

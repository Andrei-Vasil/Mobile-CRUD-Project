package com.ubb.crudapp.feature.main_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.ubb.crudapp.databinding.CatFeedCardBinding
import com.ubb.crudapp.feature.main_screen.dtos.Cat
import java.util.UUID

typealias OnButtonClickItemListener = (catId: Pair<UUID, Int>) -> Unit

class CatAdapter(private val onButtonClickListener: OnButtonClickItemListener) :
    ListAdapter<Cat, CatViewHolder>(CatDiffUtilItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CatViewHolder(
        CatFeedCardBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        onButtonClickListener
    )

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

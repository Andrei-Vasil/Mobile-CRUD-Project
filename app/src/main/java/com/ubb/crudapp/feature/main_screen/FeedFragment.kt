package com.ubb.crudapp.feature.main_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubb.crudapp.R
import com.ubb.crudapp.databinding.FragmentFeedBinding
import com.ubb.crudapp.logTag
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import java.util.UUID

class FeedFragment : Fragment(R.layout.fragment_feed) {

    private val binding by viewBinding(FragmentFeedBinding::bind)
    private val feedViewModel: FeedViewModel by activityViewModel()
    private val adapter by lazy { initAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView(LinearLayoutManager(requireContext()))
        initObservers()
        binding.addCat.setOnClickListener {
            findNavController().navigate(FeedFragmentDirections.actionFeedFragmentToNewCatFragment())
        }
    }

    private fun initAdapter(): CatAdapter = CatAdapter(::onButtonClickListener)

    private fun initRecyclerView(layoutManager: LinearLayoutManager) {
        binding.catList.adapter = adapter
        binding.catList.layoutManager = layoutManager
        feedViewModel.getCats()
    }

    private fun onButtonClickListener(pair: Pair<UUID, Int>) {
        if (pair.second == DELETE) {
            feedViewModel.deleteCat(pair.first)
        } else if (pair.second == UPDATE) {
            findNavController().navigate(FeedFragmentDirections.actionFeedFragmentToUpdateCatFragment(catId = pair.first))
        }

    }

    private fun initObservers() {
        feedViewModel.catList.observe(viewLifecycleOwner) { catList ->
            logTag("CAT_LIST", catList.size.toString())
            adapter.submitList(catList)
            adapter.notifyDataSetChanged()
        }
    }

    companion object {
        const val UPDATE = 1
        const val DELETE = 0
    }
}
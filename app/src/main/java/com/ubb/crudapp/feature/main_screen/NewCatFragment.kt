package com.ubb.crudapp.feature.main_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ubb.crudapp.R
import com.ubb.crudapp.databinding.FragmentNewCatBinding
import com.ubb.crudapp.feature.main_screen.dtos.Cat
import com.tapadoo.alerter.Alerter
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.activityViewModel


class NewCatFragment : Fragment(R.layout.fragment_new_cat) {
    private val binding by viewBinding(FragmentNewCatBinding::bind)
    private val viewModel: FeedViewModel by activityViewModel()
    private var newCat: Cat? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        binding.saveCatBTN.setOnClickListener {
            getCat()
            if (newCat != null) {
                viewModel.addCat(newCat!!)
                findNavController().navigate(NewCatFragmentDirections.actionNewCatFragmentToFeedFragment())
            }
        }
    }

    private fun getCat() {
        val name = binding.catNameTIET.text.toString()
        val age = binding.catAgeTIET.text.toString()
        val breed = binding.catBreedTIET.text.toString()
        val owner = binding.catOwnerTIET.text.toString()
        val color = binding.catColorTIET.text.toString()

        if (name.isBlank() || age.isBlank() || breed.isBlank() || owner.isBlank() || color.isBlank()) Alerter.create(
            requireActivity()
        ).setTitle("Error").setText("One or more fields are empty").setBackgroundColorRes(R.color.dark_orange).show()
        else if (!age.matches(Regex("^([1-9]+[0-9]*)|0$"))) Alerter.create(
            requireActivity()
        ).setTitle("Error").setText("Age must be a valid positive number.").setBackgroundColorRes(R.color.dark_orange).show()
        else {
            newCat = Cat(
                name = name,
                age = Integer.parseInt(age),
                breed = breed,
                owner = owner,
                color = color,
            )
        }
    }

    private fun initViews() {
        binding.toolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
        }
    }
}
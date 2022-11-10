package com.internship.crudapp.feature.main_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.internship.crudapp.R
import com.internship.crudapp.databinding.FragmentNewCatBinding
import com.internship.crudapp.feature.main_screen.dtos.Cat
import com.internship.crudapp.logTag
import com.tapadoo.alerter.Alerter
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class UpdateCatFragment : Fragment(R.layout.fragment_new_cat) {

    private val binding by viewBinding(FragmentNewCatBinding::bind)
    private val viewModel: FeedViewModel by activityViewModel()
    private var existingCat: Cat? = null
    private val args: UpdateCatFragmentArgs by navArgs()
    private var noErrors = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

        binding.saveCatBTN.setOnClickListener {
            getCat()
            if (noErrors) {
                logTag("built_cat", existingCat.toString())
                viewModel.updateCat(
                    existingCat!!.id,
                    existingCat!!.name,
                    existingCat!!.age,
                    existingCat!!.breed,
                    existingCat!!.owner,
                    existingCat!!.color,
                )
                findNavController().navigate(UpdateCatFragmentDirections.actionUpdateCatFragmentToFeedFragment())
            }
        }
    }

    private fun initViews() {
        existingCat = viewModel.getCat(args.catId)
        binding.catNameTIET.setText(existingCat?.name ?: "")
        binding.catAgeTIET.setText(existingCat?.age.toString() ?: "")
        binding.catBreedTIET.setText(existingCat?.breed ?: "")
        binding.catOwnerTIET.setText(existingCat?.owner ?: "")
        binding.catColorTIET.setText(existingCat?.color ?: "")

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun getCat() {
        val id = args.catId
        val name = binding.catNameTIET.text.toString()
        val age = binding.catAgeTIET.text.toString()
        val breed = binding.catBreedTIET.text.toString()
        val owner = binding.catOwnerTIET.text.toString()
        val color = binding.catColorTIET.text.toString()

        if (name.isBlank() || age.isBlank() || breed.isBlank() || owner.isBlank() || color.isBlank()) {
            Alerter.create(
                requireActivity()
            ).setTitle("Error").setText("One or more fields are empty")
                .setBackgroundColorRes(R.color.dark_orange).show()
            noErrors = false
        } else if (!age.matches(Regex("^([1-9]+[0-9]*)|0$"))) {
            Alerter.create(
                requireActivity()
            ).setTitle("Error").setText("Age must be a valid positive number.").setBackgroundColorRes(R.color.dark_orange).show()
            noErrors = false
        } else {
            existingCat = Cat(
                id = id,
                name = name,
                age = Integer.parseInt(age),
                breed = breed,
                owner = owner,
                color = color
            )
            noErrors = true
        }
    }

}
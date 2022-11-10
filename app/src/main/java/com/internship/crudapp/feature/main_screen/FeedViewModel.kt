package com.internship.crudapp.feature.main_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.internship.crudapp.feature.main_screen.dtos.Cat
import com.internship.crudapp.logTag
import com.internship.crudapp.model.OfflineRepository
import kotlinx.coroutines.launch
import java.util.UUID

class FeedViewModel(private val offlineRepository: OfflineRepository) : ViewModel() {

    private val cats: MutableLiveData<List<Cat>> = MutableLiveData()
    val catList: LiveData<List<Cat>> get() = cats

    fun getCats() {
        cats.value = offlineRepository.getAllCats()
    }

    fun addCat(cat: Cat) {
        viewModelScope.launch {
            try {
                offlineRepository.addCat(cat = cat)
                cats.value = offlineRepository.getAllCats()
            } catch (e: Exception) {
                logTag("cat_add", e.toString())
            }
        }
    }

    fun updateCat(id: UUID, name: String, age: Int, breed: String, owner: String, color: String) {
        viewModelScope.launch {
            try {
                val catToUpdate = Cat(id, name, age, breed, owner, color)
                offlineRepository.updateCat(cat = catToUpdate)
                cats.value = offlineRepository.getAllCats()
            } catch (e: Exception) {
                logTag("cat_update", e.toString())
            }
        }
    }

    fun deleteCat(id: UUID) {
        viewModelScope.launch {
            try {
                offlineRepository.deleteCat(id)
                cats.value = offlineRepository.getAllCats()
            } catch (e: Exception) {
                logTag("cat_delete", e.toString())
            }
        }
    }

    fun getCat(id: UUID) = offlineRepository.getCat(id)

}
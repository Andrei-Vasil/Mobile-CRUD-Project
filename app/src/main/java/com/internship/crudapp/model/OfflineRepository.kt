package com.internship.crudapp.model

import com.internship.crudapp.feature.main_screen.dtos.Cat
import com.internship.crudapp.logTag
import java.util.UUID

class OfflineRepository {
    private val catList: MutableList<Cat> = mutableListOf()

    internal fun getAllCats(): List<Cat> = catList

    internal fun addCat(cat: Cat) {
        catList.add(cat)
    }

    internal fun updateCat(cat: Cat) {
        logTag("update_cat_position_pos", cat.id.toString())
        val position = catList.indexOfFirst { it.id == cat.id }
        catList.forEach { logTag("update_cat_position_pos_list", it.id.toString()) }
        logTag("update_cat_position", position.toString())
        catList[position] = cat
    }

    internal fun deleteCat(id: UUID) {
        val position = catList.indexOfFirst { it.id == id }
        catList.removeAt(index = position)
    }

    internal fun getCat(id: UUID) = catList.first { it.id == id }
}

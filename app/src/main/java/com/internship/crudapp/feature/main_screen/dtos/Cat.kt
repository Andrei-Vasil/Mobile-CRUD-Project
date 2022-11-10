package com.internship.crudapp.feature.main_screen.dtos

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID


@Parcelize
data class Cat(
    val id: UUID = UUID.randomUUID(),
    var name: String,
    var age: Int,
    var breed: String,
    var owner: String,
    var color: String,
) : Parcelable

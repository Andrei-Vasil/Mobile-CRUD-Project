package com.internship.crudapp.model

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class InternalStorageManager(context: Context) {

    private val preferences: SharedPreferences = context.getSharedPreferences(KEY_PREFERENCES, MODE_PRIVATE)


    companion object {
        const val KEY_PREFERENCES = "com.internship.crudappp.KEY_PREFERENCES"
    }
}
package com.ubb.crudapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ubb.crudapp.feature.main_screen.FeedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val feedViewModel: FeedViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
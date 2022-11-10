package com.ubb.crudapp.model

import com.ubb.crudapp.feature.main_screen.FeedViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModel = module {
    viewModel { FeedViewModel(offlineRepository = get()) }
}

val repository = module {
    single<OfflineRepository> { OfflineRepository() }
}

val internalStorage = module {
    single { InternalStorageManager(androidContext()) }
}
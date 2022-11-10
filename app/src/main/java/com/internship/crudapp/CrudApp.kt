package com.internship.crudapp

import android.app.Application
import com.internship.crudapp.model.internalStorage
import com.internship.crudapp.model.repository
import com.internship.crudapp.model.viewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CrudApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CrudApp)
            modules(
                listOf(
                    internalStorage,
                    repository,
                    viewModel
                )
            )
        }
    }
}
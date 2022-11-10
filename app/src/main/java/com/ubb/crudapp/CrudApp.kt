package com.ubb.crudapp

import android.app.Application
import com.ubb.crudapp.model.internalStorage
import com.ubb.crudapp.model.repository
import com.ubb.crudapp.model.viewModel
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
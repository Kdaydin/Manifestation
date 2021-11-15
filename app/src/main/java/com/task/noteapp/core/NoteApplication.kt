package com.task.noteapp.core

import android.app.Application
import com.task.noteapp.di.databaseModule
import com.task.noteapp.di.repositoryModule
import com.task.noteapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NoteApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@NoteApplication)
            modules(
                databaseModule,
                viewModelModule,
                repositoryModule
            )
        }
    }
}
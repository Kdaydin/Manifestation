package com.task.noteapp.core

import android.app.Application
import org.koin.core.context.startKoin

class NoteApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {  }
    }
}
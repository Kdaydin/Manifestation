package com.task.noteapp.di

import android.app.Application
import androidx.room.Room
import com.task.noteapp.db.NotesDao
import com.task.noteapp.db.NotesDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(application: Application): NotesDatabase {
        return Room.databaseBuilder(application, NotesDatabase::class.java, "notes")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideNotesDao(database: NotesDatabase): NotesDao {
        return database.notesDao
    }

    single { provideDatabase(androidApplication()) }
    single { provideNotesDao(get()) }


}
package com.task.noteapp.di

import com.task.noteapp.db.NotesDao
import com.task.noteapp.repository.NotesRepository
import com.task.noteapp.repository.NotesRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    fun provideNotesRepository(dao: NotesDao): NotesRepository {
        return NotesRepositoryImpl(dao)
    }

    single { provideNotesRepository(get()) }
}
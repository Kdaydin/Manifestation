package com.task.noteapp.di

import com.task.noteapp.viewmodel.EditNoteViewModel
import com.task.noteapp.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(notesRepository = get()) }
    viewModel { EditNoteViewModel(notesRepository = get()) }
}
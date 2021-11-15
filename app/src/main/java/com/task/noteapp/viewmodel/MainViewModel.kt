package com.task.noteapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.noteapp.db.entities.NoteEntity
import com.task.noteapp.repository.NotesRepository
import kotlinx.coroutines.launch

class MainViewModel(private val notesRepository: NotesRepository) : ViewModel() {

    val uiState = MutableLiveData<MainUIState>()
    val notes = MutableLiveData<List<NoteEntity>>()

    fun getAllNotes() {
        viewModelScope.launch {
            val result = notesRepository.getAllNotes()
            notes.postValue(result)
            if (result.isNullOrEmpty()) {
                uiState.postValue(MainUIState.EmptyList())
            } else {
                uiState.postValue(MainUIState.NoteList())
            }
        }
    }

    fun addNote(noteEntity: NoteEntity) {
        viewModelScope.launch {
            val result = notesRepository.addNote(noteEntity)
            notes.postValue(result)
            if (result.isNullOrEmpty()) {
                uiState.postValue(MainUIState.EmptyList())
            } else {
                uiState.postValue(MainUIState.NoteList())
            }
        }
    }

    fun deleteNote(noteEntity: NoteEntity) {
        viewModelScope.launch {
            val result = notesRepository.deleteNote(noteEntity)
            notes.postValue(result)
            if (result.isNullOrEmpty()) {
                uiState.postValue(MainUIState.EmptyList())
            } else {
                uiState.postValue(MainUIState.NoteList())
            }
        }
    }

    fun addNewNote() {
        uiState.postValue(MainUIState.NewNote())
    }
}

interface MainUIState {
    class EmptyList : MainUIState
    class NoteList : MainUIState
    class NewNote : MainUIState
}
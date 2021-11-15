package com.task.noteapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.noteapp.db.entities.NoteEntity
import com.task.noteapp.repository.NotesRepository
import kotlinx.coroutines.launch
import java.util.*

class EditNoteViewModel(private val notesRepository: NotesRepository) : ViewModel() {

    val uiState = MutableLiveData<EditUIState>()
    var note: NoteEntity? = null
    val title = MutableLiveData("")
    val description = MutableLiveData("")
    val imgUrl = MutableLiveData("")

    fun addNote() {
        viewModelScope.launch {
            if (note != null) {
                (note as NoteEntity).title = title.value
                (note as NoteEntity).description = description.value
                (note as NoteEntity).imgUrl = imgUrl.value
                (note as NoteEntity).isEdited = true
            }

            val result = notesRepository.addNote(
                note ?: NoteEntity(
                    Date().time,
                    title.value,
                    description.value,
                    imgUrl.value,
                    false
                )
            )
            if (result.isNullOrEmpty().not()) {
                uiState.postValue(EditUIState.DataSaved())
            } else {
                uiState.postValue(EditUIState.Error())
            }
        }
    }

}

interface EditUIState {
    class DataSaved : EditUIState
    class Error : EditUIState
}
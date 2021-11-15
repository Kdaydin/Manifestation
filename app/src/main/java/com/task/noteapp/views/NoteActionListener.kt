package com.task.noteapp.views

import com.task.noteapp.db.entities.NoteEntity

interface NoteActionListener {
    fun editNote(noteEntity: NoteEntity)
    fun deleteNote(noteEntity: NoteEntity)
}
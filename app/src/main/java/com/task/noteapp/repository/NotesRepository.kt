package com.task.noteapp.repository

import com.task.noteapp.db.entities.NoteEntity

interface NotesRepository {
    suspend fun getAllNotes(): List<NoteEntity>
    suspend fun addNote(noteEntity: NoteEntity): List<NoteEntity>
    suspend fun deleteNote(noteEntity: NoteEntity): List<NoteEntity>
}
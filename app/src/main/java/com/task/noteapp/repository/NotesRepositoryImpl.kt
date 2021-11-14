package com.task.noteapp.repository

import com.task.noteapp.db.NotesDao
import com.task.noteapp.db.entities.NoteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NotesRepositoryImpl(
    private val dao: NotesDao
) : NotesRepository {
    override suspend fun getAllNotes(): List<NoteEntity> {
        return withContext(Dispatchers.IO) {
            dao.getAllNotes()
        }
    }

    override suspend fun addNote(noteEntity: NoteEntity): List<NoteEntity> {
        return withContext(Dispatchers.IO) {
            dao.addNote(noteEntity)
            return@withContext dao.getAllNotes()
        }
    }

    override suspend fun deleteNote(noteEntity: NoteEntity): List<NoteEntity> {
        return withContext(Dispatchers.IO) {
            dao.deleteNote(noteEntity)
            return@withContext dao.getAllNotes()
        }
    }
}
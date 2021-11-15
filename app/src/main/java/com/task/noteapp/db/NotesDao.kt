package com.task.noteapp.db

import androidx.room.*
import com.task.noteapp.db.entities.NoteEntity

@Dao
interface NotesDao {
    @Query("SELECT * FROM notes")
    fun getAllNotes(): List<NoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNote(note: NoteEntity)

    @Delete
    fun deleteNote(note: NoteEntity)
}
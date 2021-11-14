package com.task.noteapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.task.noteapp.db.entities.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 1,
    exportSchema = false
)

abstract class NotesDatabase : RoomDatabase() {
    abstract val notesDao: NotesDao
}
package com.task.noteapp.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey val createDate: Long,
    val title: String?,
    val description: String?,
    val imgUrl: String?,
    val isEdited: Boolean?
)

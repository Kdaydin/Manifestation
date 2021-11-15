package com.task.noteapp.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey val createDate: Long,
    var title: String?,
    var description: String?,
    var imgUrl: String?,
    var isEdited: Boolean?
):Serializable

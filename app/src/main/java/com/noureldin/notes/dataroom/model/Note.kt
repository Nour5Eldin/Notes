package com.noureldin.notes.dataroom.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity( "Notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Int =0,
    var title: String,
    var details: String,
    var date: String,
    var isArchived: Boolean = false,
    var priority: String
)

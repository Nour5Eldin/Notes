package com.noureldin.notes.dataroom.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.noureldin.notes.dataroom.model.Note
@Dao
interface NotesDao {
    @Query("SELECT * FROM Notes")
    fun getNotes(): List<Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(notes: Note)

    @Query("DELETE  FROM Notes WHERE id=:id ")
    fun deleteNotes(id: Int)

    @Update
    fun updateNotes(notes: Note)
}
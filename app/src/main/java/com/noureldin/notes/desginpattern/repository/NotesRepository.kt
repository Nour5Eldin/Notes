package com.noureldin.notes.desginpattern.repository

import com.noureldin.notes.dataroom.dao.NotesDao
import com.noureldin.notes.dataroom.model.Note

class NotesRepository(val dao: NotesDao) {
    fun getAllNotes():List<Note>{
        return dao.getNotes()
    }
    fun insertNotes(notes: Note){
        dao.insertNotes(notes)
    }
    fun deleteNotes(id:Int){
        dao.deleteNotes(id)
    }
    fun updateNotes(notes: Note){
        dao.updateNotes(notes)
    }
}
package com.noureldin.notes.desginpattern.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.noureldin.notes.dataroom.database.NotesDatabase
import com.noureldin.notes.dataroom.model.Note
import com.noureldin.notes.desginpattern.repository.NotesRepository

class NotesViewModel(application: Application): AndroidViewModel(application) {

    val repository: NotesRepository

    var notesList = MutableLiveData<List<Note>>()

    init {
        val dao= NotesDatabase.getDatabaseInstance(application).myNotesDao()
        repository= NotesRepository(dao)
    }
    fun addNotes(notes: Note){
        repository.insertNotes(notes)
    }
    fun getNotes() {
        notesList.value = repository.getAllNotes()
    }

    fun deleteNotes(id:Int)
    {
//        notesList.value = notesList.value!!.toMutableList().removeAt()
        repository.deleteNotes(id)
    }
     fun updateNotes(notes: Note)
     {
         repository.updateNotes(notes)
     }
}

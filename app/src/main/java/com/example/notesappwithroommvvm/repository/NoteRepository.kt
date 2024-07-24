package com.example.notesappwithroommvvm.repository

import com.example.notesappwithroommvvm.database.NoteDatabase
import com.example.notesappwithroommvvm.model.Note

class NoteRepository(private val db:NoteDatabase) {

    suspend fun insertNote (note: Note)=db.getDao().insertNote(note)
    suspend fun updateNote (note: Note)=db.getDao().updateNote(note)
    suspend fun deleteNote (note: Note)=db.getDao().deleteNote(note)

    fun getAllNotes() =db.getDao().getAllNotes()
    fun searchNote (query:String?)=db.getDao().searchNote(query)

}
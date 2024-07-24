package com.example.notesappwithroommvvm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesappwithroommvvm.model.Note
import com.example.notesappwithroommvvm.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel (application: Application , private val noteRepository: NoteRepository )
    : AndroidViewModel(application) {

    fun addNote(note: Note) =
        viewModelScope.launch {
            noteRepository.insertNote(note)
        }
    fun deleteNote(note: Note) =
        viewModelScope.launch {
            noteRepository.deleteNote(note)
        }
    fun updateNote(note: Note) =
        viewModelScope.launch {
            noteRepository.updateNote(note)
        }
    fun getAllData()= noteRepository.getAllNotes()

    fun searchNote(query:String?) = noteRepository.searchNote(query)


}
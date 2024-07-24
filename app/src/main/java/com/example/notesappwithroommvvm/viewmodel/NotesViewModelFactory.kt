package com.example.notesappwithroommvvm.viewmodel

import android.app.Application
import androidx.compose.ui.window.application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notesappwithroommvvm.repository.NoteRepository

@Suppress("UNCHECKED_CAST")
class NotesViewModelFactory(private val application: Application, private val noteRepository: NoteRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteViewModel(application ,noteRepository ) as T

    }


}
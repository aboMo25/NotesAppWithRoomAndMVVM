package com.example.notesappwithroommvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.notesappwithroommvvm.database.NoteDatabase
import com.example.notesappwithroommvvm.repository.NoteRepository
import com.example.notesappwithroommvvm.viewmodel.NoteViewModel
import com.example.notesappwithroommvvm.viewmodel.NotesViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var noteViewModel: NoteViewModel
    private lateinit var noteDatabase: NoteDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()

    }

    private fun setupViewModel(){

        val noteRepository=NoteRepository(NoteDatabase(this))
        val viewModelProviderFactory=NotesViewModelFactory(application,noteRepository)
        noteViewModel = ViewModelProvider(this,viewModelProviderFactory)[NoteViewModel::class.java]

    }


}
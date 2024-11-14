package com.example.noteappusingroomdatabase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.noteappusingroomdatabase.roomdatabase.NoteDatabase
import com.example.noteappusingroomdatabase.roomdatabase.note.Note
import com.example.noteappusingroomdatabase.roomdatabase.note.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {

    val readAllDatabase: LiveData<List<Note>>
    private val repository: NoteRepository

    init {
        val noteDao = NoteDatabase.getInstance(application).noteDao()
        repository = NoteRepository(noteDao)
        readAllDatabase = repository.readAllDatabase
    }

    fun upsertNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.upsertNote(note)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(note)
        }
    }
}
package com.example.noteappusingroomdatabase.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
<<<<<<< HEAD
import com.example.noteappusingroomdatabase.roomdatabase.note.Note
<<<<<<< HEAD
import com.example.noteappusingroomdatabase.roomdatabase.note.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
=======
import com.example.noteappusingroomdatabase.roomdatabase.NoteDatabase
import com.example.noteappusingroomdatabase.roomdatabase.note.NoteRepository
>>>>>>> redoNewBranch
=======
import com.example.noteappusingroomdatabase.roomdatabase.Note
import com.example.noteappusingroomdatabase.roomdatabase.NoteDatabase
import com.example.noteappusingroomdatabase.roomdatabase.NoteRepository
>>>>>>> parent of 43a6b8b (new update)
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository
): ViewModel() {

    val readAllDatabase: LiveData<List<Note>> = noteRepository.readAllDatabase

    fun upsertNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.upsertNote(note)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.deleteNote(note)
        }
    }
}
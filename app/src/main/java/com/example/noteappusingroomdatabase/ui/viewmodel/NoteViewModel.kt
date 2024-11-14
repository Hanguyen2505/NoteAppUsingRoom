package com.example.noteappusingroomdatabase.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteappusingroomdatabase.roomdatabase.note.Note
import com.example.noteappusingroomdatabase.roomdatabase.note.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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
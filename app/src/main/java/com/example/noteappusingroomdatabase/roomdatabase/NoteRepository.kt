package com.example.noteappusingroomdatabase.roomdatabase

import androidx.lifecycle.LiveData
import com.example.noteappusingroomdatabase.roomdatabase.Note
import com.example.noteappusingroomdatabase.roomdatabase.NoteDao

class NoteRepository(private val noteDao: NoteDao) {
    val readAllDatabase: LiveData<List<Note>> = noteDao.readAllNotesData()

    suspend fun upsertNote(note: Note) {
        noteDao.upsertNote(note)
    }

    suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }

}
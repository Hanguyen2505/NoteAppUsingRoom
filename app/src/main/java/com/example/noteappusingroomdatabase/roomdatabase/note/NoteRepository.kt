package com.example.noteappusingroomdatabase.roomdatabase.note

import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao: NoteDao) {
    val readAllDatabase: LiveData<List<Note>> = noteDao.readAllNotesData()


    suspend fun upsertNote(note: Note) {
        noteDao.upsertNote(note)
    }

    suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }

    fun getNotesByUserId(userId: String) = noteDao.getNotesByUserId(userId)

}
package com.example.noteappusingroomdatabase.roomdatabase.note

import androidx.lifecycle.LiveData
import javax.inject.Inject


class NoteRepository @Inject constructor(
    private val noteDao: NoteDao
) {
    val readAllDatabase: LiveData<List<Note>> = noteDao.readAllNotesData()

    suspend fun upsertNote(note: Note) {
        noteDao.upsertNote(note)
    }

    suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }

    suspend fun getNoteById(noteId: Int): List<Note> {
        return noteDao.getNotesByUserId(noteId)
    }

}
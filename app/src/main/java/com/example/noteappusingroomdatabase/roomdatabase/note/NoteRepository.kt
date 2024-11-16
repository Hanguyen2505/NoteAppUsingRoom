package com.example.noteappusingroomdatabase.roomdatabase.note

import androidx.lifecycle.LiveData
<<<<<<< HEAD
import javax.inject.Inject


class NoteRepository @Inject constructor(
    private val noteDao: NoteDao
) {
    val readAllDatabase: LiveData<List<Note>> = noteDao.readAllNotesData()

=======

class NoteRepository(private val noteDao: NoteDao) {
    val readAllDatabase: LiveData<List<Note>> = noteDao.readAllNotesData()


>>>>>>> redoNewBranch
    suspend fun upsertNote(note: Note) {
        noteDao.upsertNote(note)
    }

    suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }

<<<<<<< HEAD
    suspend fun getNoteById(noteId: Int): List<Note> {
        return noteDao.getNotesByUserId(noteId)
    }
=======
    fun getNotesByUserId(userId: String) = noteDao.getNotesByUserId(userId)
>>>>>>> redoNewBranch

}
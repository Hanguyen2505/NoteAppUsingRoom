package com.example.noteappusingroomdatabase.roomdatabase

import androidx.lifecycle.LiveData
<<<<<<< HEAD:app/src/main/java/com/example/noteappusingroomdatabase/roomdatabase/note/NoteRepository.kt
<<<<<<< HEAD
import javax.inject.Inject


class NoteRepository @Inject constructor(
    private val noteDao: NoteDao
) {
    val readAllDatabase: LiveData<List<Note>> = noteDao.readAllNotesData()

=======
=======
import com.example.noteappusingroomdatabase.roomdatabase.Note
import com.example.noteappusingroomdatabase.roomdatabase.NoteDao
>>>>>>> parent of 43a6b8b (new update):app/src/main/java/com/example/noteappusingroomdatabase/roomdatabase/NoteRepository.kt

class NoteRepository(private val noteDao: NoteDao) {
    val readAllDatabase: LiveData<List<Note>> = noteDao.readAllNotesData()

<<<<<<< HEAD:app/src/main/java/com/example/noteappusingroomdatabase/roomdatabase/note/NoteRepository.kt

>>>>>>> redoNewBranch
=======
>>>>>>> parent of 43a6b8b (new update):app/src/main/java/com/example/noteappusingroomdatabase/roomdatabase/NoteRepository.kt
    suspend fun upsertNote(note: Note) {
        noteDao.upsertNote(note)
    }

    suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }

<<<<<<< HEAD:app/src/main/java/com/example/noteappusingroomdatabase/roomdatabase/note/NoteRepository.kt
<<<<<<< HEAD
    suspend fun getNoteById(noteId: Int): List<Note> {
        return noteDao.getNotesByUserId(noteId)
    }
=======
    fun getNotesByUserId(userId: String) = noteDao.getNotesByUserId(userId)
>>>>>>> redoNewBranch

=======
>>>>>>> parent of 43a6b8b (new update):app/src/main/java/com/example/noteappusingroomdatabase/roomdatabase/NoteRepository.kt
}
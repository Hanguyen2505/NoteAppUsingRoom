package com.example.noteappusingroomdatabase.roomdatabase.note

import android.util.Log
import androidx.lifecycle.LiveData
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class NoteRepository(private val noteDao: NoteDao) {
//    val readAllDatabase: List<Note> = noteDao.readAllNotesData()

    private val noteCollectionRef = FirebaseFirestore.getInstance().collection("notes")

    suspend fun upsertNote(note: Note) {
        noteDao.upsertNote(note)
    }

    suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }

    suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }

    suspend fun syncNotes() {
        val notesFromLocal = noteDao.readAllNotesData()
        Log.d("SyncWorker", "noteFromLocal: $notesFromLocal")
        for (note in notesFromLocal) {
            noteCollectionRef.document(note.id.toString()).set(note).await()
        }

        val notesFromRemote = noteCollectionRef.get().await().toObjects(Note::class.java)
        for (note in notesFromRemote) {
            upsertNote(note)
        }
    }

    fun getNotesByUserId(userId: String) = noteDao.getNotesByUserId(userId)

}
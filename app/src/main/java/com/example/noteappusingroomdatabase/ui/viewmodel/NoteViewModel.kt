package com.example.noteappusingroomdatabase.ui.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.noteappusingroomdatabase.manager.SyncWorker
import com.example.noteappusingroomdatabase.roomdatabase.note.Note
import com.example.noteappusingroomdatabase.roomdatabase.NoteDatabase
import com.example.noteappusingroomdatabase.roomdatabase.note.NoteRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class NoteViewModel(application: Application): AndroidViewModel(application) {

    private val repository: NoteRepository

    private val noteCollectionRef = FirebaseFirestore.getInstance().collection("notes")

    init {
        val noteDao = NoteDatabase.getInstance(application).noteDao()
        repository = NoteRepository(noteDao)
    }

    fun upsertNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.upsertNote(note)
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNote(note)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(note)
        }
    }

    fun getNotesByUserId(userId: String): LiveData<List<Note>> {
        return repository.getNotesByUserId(userId)

    }

    fun uploadNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        try {
            noteCollectionRef.document(note.id.toString()).set(note).await()
            withContext(Dispatchers.Main) {
                Toast.makeText(getApplication(), "Successfully uploaded", Toast.LENGTH_SHORT).show()
            }
        }
        catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(getApplication(), e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun retrieveNotes() = viewModelScope.launch(Dispatchers.IO) {
        val allNoteFromRemote = noteCollectionRef.get().await().toObjects(Note::class.java)
        for (note in allNoteFromRemote) {
            Log.d("retrieveNotes", "retrieveNotes: $note")
            upsertNote(note)
        }
    }

    fun deleteNoteFromRemote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        try {
            noteCollectionRef.document(note.id.toString()).delete().await()
            withContext(Dispatchers.Main) {
                Toast.makeText(getApplication(), "Successfully deleted", Toast.LENGTH_SHORT).show()
            }
        }
        catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(getApplication(), e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun setupWorkRequest() {
        val syncWorkerRequest = PeriodicWorkRequestBuilder<SyncWorker>(
            1,
            java.util.concurrent.TimeUnit.MINUTES
        ).setConstraints(
            Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        ).build()

        WorkManager.getInstance(getApplication()).enqueueUniquePeriodicWork(
            "SyncNetwork",
            ExistingPeriodicWorkPolicy.KEEP,//keep the existing work policy if one already exists
            syncWorkerRequest
        )

    }
}
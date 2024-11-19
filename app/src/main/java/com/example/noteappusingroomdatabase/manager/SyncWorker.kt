package com.example.noteappusingroomdatabase.manager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.noteappusingroomdatabase.roomdatabase.NoteDatabase
import com.example.noteappusingroomdatabase.roomdatabase.note.NoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SyncWorker(
    context: Context,
    workerParams: WorkerParameters
): Worker(context, workerParams) {

    private val noteRepository: NoteRepository by lazy {
        // Initialize your repository (e.g., using Room database instance)
        val database = NoteDatabase.getInstance(context)
        NoteRepository(database.noteDao())
    }
    override fun doWork(): Result {
        return try {
            CoroutineScope(Dispatchers.IO).launch {
                noteRepository.syncNotes()
            }
            Result.success()
        } catch (e: Exception) {
            Log.d("SyncWorker", "doWork error: $e")
            Result.retry()
        }
    }
}
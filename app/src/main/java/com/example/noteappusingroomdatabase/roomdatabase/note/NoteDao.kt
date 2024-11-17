package com.example.noteappusingroomdatabase.roomdatabase.note

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface NoteDao {

    @Upsert
    suspend fun upsertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM note ORDER BY id ASC")
    fun readAllNotesData(): LiveData<List<Note>>

    @Query("SELECT * FROM note WHERE userId = :userId")
<<<<<<< HEAD
    suspend fun getNotesByUserId(userId: Int): List<Note>
=======
    fun getNotesByUserId(userId: String): LiveData<List<Note>>

>>>>>>> redoNewBranch
}
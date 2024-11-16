package com.example.noteappusingroomdatabase.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.noteappusingroomdatabase.roomdatabase.Note

@Dao
interface NoteDao {

    @Upsert
    suspend fun upsertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM note ORDER BY id ASC")
    fun readAllNotesData(): LiveData<List<Note>>
<<<<<<< HEAD:app/src/main/java/com/example/noteappusingroomdatabase/roomdatabase/note/NoteDao.kt

    @Query("SELECT * FROM note WHERE userId = :userId")
<<<<<<< HEAD
    suspend fun getNotesByUserId(userId: Int): List<Note>
=======
    fun getNotesByUserId(userId: String): LiveData<List<Note>>

>>>>>>> redoNewBranch
=======
>>>>>>> parent of 43a6b8b (new update):app/src/main/java/com/example/noteappusingroomdatabase/roomdatabase/NoteDao.kt
}
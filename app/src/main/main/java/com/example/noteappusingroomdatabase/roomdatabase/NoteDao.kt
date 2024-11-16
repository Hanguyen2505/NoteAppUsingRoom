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
}
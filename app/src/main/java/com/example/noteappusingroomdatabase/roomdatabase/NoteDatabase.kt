package com.example.noteappusingroomdatabase.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
)
abstract class NoteDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getInstance(context: Context): NoteDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
<<<<<<< HEAD
<<<<<<< HEAD
                    DATABASE_NAME
=======
                    "Note_database_user_note"
>>>>>>> redoNewBranch
=======
                    "Note_db_new"
>>>>>>> parent of 43a6b8b (new update)
                ).build().also {
                    INSTANCE = it
                }
            }
        }

        private const val DATABASE_NAME: String = "note_database"
    }
}
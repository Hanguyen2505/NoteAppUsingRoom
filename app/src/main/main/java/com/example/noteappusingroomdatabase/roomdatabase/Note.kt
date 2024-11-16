package com.example.noteappusingroomdatabase.roomdatabase

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "note")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val title: String,
    val subTitle: String,
    val noteInput: String,
    val noteColor: String
): Parcelable

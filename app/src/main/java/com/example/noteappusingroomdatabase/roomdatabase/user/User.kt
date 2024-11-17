package com.example.noteappusingroomdatabase.roomdatabase.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey
    val userId: String,
    val email: String,
    val password: String
)
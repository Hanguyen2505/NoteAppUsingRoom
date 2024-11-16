package com.example.noteappusingroomdatabase.roomdatabase.user

import androidx.room.Entity
import androidx.room.PrimaryKey

<<<<<<< HEAD
@Entity(tableName = "users")
=======
@Entity(tableName = "user")
>>>>>>> redoNewBranch
data class User(
    @PrimaryKey
    val userId: String,
    val email: String,
    val password: String
<<<<<<< HEAD
)
=======
)
>>>>>>> redoNewBranch

package com.example.noteappusingroomdatabase.roomdatabase.note

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.noteappusingroomdatabase.roomdatabase.user.User
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(
    tableName = "note",
    foreignKeys = [ForeignKey(
<<<<<<< HEAD
    entity = User::class,
        parentColumns = ["userId"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE)
    ],
=======
        entity = User::class,
        parentColumns = ["userId"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )],
>>>>>>> redoNewBranch
    indices = [Index(value = ["userId"])]
)
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val userId: String,
    val title: String,
    val subTitle: String,
    val noteInput: String,
    val noteColor: String
): Parcelable

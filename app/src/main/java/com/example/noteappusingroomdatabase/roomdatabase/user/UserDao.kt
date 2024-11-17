package com.example.noteappusingroomdatabase.roomdatabase.user

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface UserDao {

    @Upsert
    suspend fun upsertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM user ORDER BY userId ASC")
    fun readAllUserData(): LiveData<List<User>>

    @Query("SELECT * FROM user WHERE userId = :userId")
    fun getUserById(userId: String): User
}
package com.example.noteappusingroomdatabase.roomdatabase.user

import androidx.lifecycle.LiveData
import androidx.room.Dao
<<<<<<< HEAD
=======
import androidx.room.Delete
>>>>>>> redoNewBranch
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface UserDao {

    @Upsert
    suspend fun upsertUser(user: User)

<<<<<<< HEAD
    @Query("SELECT * FROM users WHERE userId = :userId")
    suspend fun getUserById(userId: String): User?

    @Query("SELECT * FROM users ORDER BY userId ASC")
    fun readAllUser(): LiveData<List<User>>

=======
    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM user ORDER BY userId ASC")
    fun readAllUserData(): LiveData<List<User>>

    @Query("SELECT * FROM user WHERE userId = :userId")
    fun getUserById(userId: String): User
>>>>>>> redoNewBranch
}
package com.example.noteappusingroomdatabase.roomdatabase.user

<<<<<<< HEAD
import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
) {
    private val firebaseAuth = FirebaseAuth.getInstance()

    suspend fun registerUser(email: String, password: String): Result<User> {
=======
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await


class UserRepository(
    private val userDao: UserDao
) {
    val readAllUserDatabase: LiveData<List<User>> = userDao.readAllUserData()

    private val _currentUser = MutableLiveData<FirebaseUser?>()

    val currentUser: LiveData<FirebaseUser?>
        get() = _currentUser

    private val firebaseAuth = FirebaseAuth.getInstance()

    init {
        firebaseAuth.addAuthStateListener { auth ->
            _currentUser.value = auth.currentUser
        }
    }

    private suspend fun upsertUser(user: User) {
        userDao.upsertUser(user)
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)

    }

    suspend fun registerUser(email: String, password: String): Result<FirebaseUser> {
>>>>>>> redoNewBranch
        return try {
            val authResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val firebaseUser = authResult.user
            if (firebaseUser != null) {
<<<<<<< HEAD
                val user = User(firebaseAuth.currentUser!!.uid ,email, password)
                upsertUser(user)
                Result.success(user)
=======
                val user = User(firebaseUser.uid, email, password)
                upsertUser(user) // Save user locally
                Result.success(firebaseUser)
>>>>>>> redoNewBranch
            } else {
                Result.failure(Exception("Registration failed"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

<<<<<<< HEAD
    suspend fun loginUser(email: String, password: String): Result<User?> {
        return try {
            val authResult = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            val firebaseUser = authResult.user
            if (firebaseUser != null) {
                val user = userDao.getUserById(firebaseUser.uid)
                Result.success(user)
            } else {
                Result.failure(Exception("Login failed"))
=======
    suspend fun loginUser(email: String, password: String): Result<FirebaseUser> {
        return try {
            val authResult = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            val firebaseUser = authResult.user

            if (firebaseUser != null) {
                val user = User(firebaseUser.uid, email, password)
                upsertUser(user) // Save user locally
                Result.success(firebaseUser)
            } else {
                Result.failure(Exception("Registration failed"))
>>>>>>> redoNewBranch
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

<<<<<<< HEAD
    val readAllUser: LiveData<List<User>> = userDao.readAllUser()
    private suspend fun upsertUser(user: User) {
        userDao.upsertUser(user)
    }

    suspend fun getUserById(userId: String): User? {
        return userDao.getUserById(userId)
    }
}
=======
}
>>>>>>> redoNewBranch

package com.example.noteappusingroomdatabase.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.noteappusingroomdatabase.roomdatabase.NoteDatabase
import com.example.noteappusingroomdatabase.roomdatabase.user.User
import com.example.noteappusingroomdatabase.roomdatabase.user.UserRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    private val readAllUserDatabase: LiveData<List<User>>
    private val repository: UserRepository

    val currentUser: LiveData<FirebaseUser?>

    val loginResult = MutableLiveData<Result<FirebaseUser>>()

    init {
        val userDao = NoteDatabase.getInstance(application).userDao()
        repository = UserRepository(userDao)
        readAllUserDatabase = repository.readAllUserDatabase

        currentUser = repository.currentUser
    }

    fun registerUser(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.registerUser(email, password)
            loginResult.postValue(result)
        }

    }

    fun loginUser(email: String, password: String){
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.loginUser(email, password)
            loginResult.postValue(result)
            if (result.isSuccess) {
                val user = result.getOrNull()
                Log.d("currentUserLoggedIn", "${user?.email}")
            }
            else {
                val exception = result.exceptionOrNull()
                Log.d("currentUserLoggedIn", "${exception?.message}")

            }
        }
    }

}
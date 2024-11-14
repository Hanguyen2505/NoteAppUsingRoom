package com.example.noteappusingroomdatabase.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteappusingroomdatabase.roomdatabase.user.User
import com.example.noteappusingroomdatabase.roomdatabase.user.UserRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {
    private val firebaseAuth = FirebaseAuth.getInstance()

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> = _user

    fun registerUser(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = userRepository.registerUser(email, password)
            if (result.isSuccess) {
                _user.postValue(result.getOrNull())
            }
        }
    }

    fun loginUser(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = userRepository.loginUser(email, password)
            if (result.isSuccess) {
                _user.postValue(result.getOrNull())
            }

        }
    }

}
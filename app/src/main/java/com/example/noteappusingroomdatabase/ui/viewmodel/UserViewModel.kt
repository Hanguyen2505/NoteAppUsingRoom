package com.example.noteappusingroomdatabase.ui.viewmodel

<<<<<<< HEAD
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

=======
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
>>>>>>> redoNewBranch
        }
    }

}
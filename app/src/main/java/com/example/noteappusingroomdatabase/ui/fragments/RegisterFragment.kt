package com.example.noteappusingroomdatabase.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.noteappusingroomdatabase.R
import com.example.noteappusingroomdatabase.databinding.FragmentRegisterBinding
import com.example.noteappusingroomdatabase.ui.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    private val mUserViewModel: UserViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)

        registerUser()

        return binding.root
    }

    private fun registerUser() {
        val email = binding.signupEmail.text.toString()
        val password = binding.signupPassword.text.toString()
        val confirmPassword = binding.signupCfPassword.text.toString()

        if (password == confirmPassword) {
            mUserViewModel.registerUser(email, password)
        } else {
            binding.signupPassword.error = "Password does not match"
            binding.signupCfPassword.error = "Password does not match"
        }

    }

}
package com.example.noteappusingroomdatabase.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.noteappusingroomdatabase.R
import com.example.noteappusingroomdatabase.databinding.FragmentLogInBinding
import com.example.noteappusingroomdatabase.ui.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogInFragment : Fragment() {

    private lateinit var binding: FragmentLogInBinding

    private val mUserViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogInBinding.inflate(inflater, container, false)

        binding.fpButton.setOnClickListener {
            val action = LogInFragmentDirections.actionLogInFragmentToForgotPasswordFragment2()
            findNavController().navigate(action)

        }

        binding.signupButton.setOnClickListener {
            val action = LogInFragmentDirections.actionLogInFragmentToRegisterFragment()
            findNavController().navigate(action)
        }

        loginUser()

        mUserViewModel.user.observe(viewLifecycleOwner) { user ->

        }

        return binding.root
    }

    private fun loginUser() {
        binding.apply {
            val email = loginEmail.text.toString()
            val password = loginPassword.text.toString()
            login.setOnClickListener {
                mUserViewModel.loginUser(email, password)
            }

        }
    }

}
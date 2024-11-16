package com.example.noteappusingroomdatabase.ui.fragments.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.noteappusingroomdatabase.R
import com.example.noteappusingroomdatabase.databinding.FragmentRegisterBinding
import com.example.noteappusingroomdatabase.ui.viewmodel.NoteViewModel
import com.example.noteappusingroomdatabase.ui.viewmodel.UserViewModel


class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    private lateinit var mUserViewModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        binding.signup.setOnClickListener {
            registerUser()

            mUserViewModel.loginResult.observe(viewLifecycleOwner) { result ->
                result.onSuccess {
                    Toast.makeText(requireContext(), "Registration successful", Toast.LENGTH_SHORT).show()
                    val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                    findNavController().navigate(action)
                }
                result.onFailure { exception ->
                    Toast.makeText(requireContext(), "Registration failed: ${exception.message}", Toast.LENGTH_SHORT).show()
                }
            }


        }



        binding.goBack.setOnClickListener {
            val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            findNavController().navigate(action)

        }

        return binding.root
    }

    private fun registerUser() {
        val email = binding.signupEmail.text.toString()
        val password = binding.signupPassword.text.toString()
        val confirmPassword = binding.signupCfPassword.text.toString()

        Log.d("registerUSer", "$email, $password, $confirmPassword")

        if (password == confirmPassword) {
            mUserViewModel.registerUser(email, password)
        }
        else {
            Toast.makeText(requireContext(), "Password not match", Toast.LENGTH_SHORT).show()
        }



    }

}
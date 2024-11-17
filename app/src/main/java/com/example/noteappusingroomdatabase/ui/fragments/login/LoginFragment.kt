package com.example.noteappusingroomdatabase.ui.fragments.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.noteappusingroomdatabase.R
import com.example.noteappusingroomdatabase.databinding.FragmentLoginBinding
import com.example.noteappusingroomdatabase.ui.activity.MainActivity
import com.example.noteappusingroomdatabase.ui.viewmodel.UserViewModel


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        binding.fpButton.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().navigate(action)
        }

        binding.signup.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().navigate(action)
        }

        binding.login.setOnClickListener {
            loginUser()
            mUserViewModel.loginResult.observe(viewLifecycleOwner) { result ->
                result.onSuccess {
                    val user = result.getOrNull()
                    Log.d("currentUserLoggedIn", "${user?.email}")
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    startActivity(intent)
                }
                result.onFailure {
                    val exception = result.exceptionOrNull()
                    Toast.makeText(requireContext(), "Login failed: ${exception?.message}", Toast.LENGTH_LONG).show()
                }
            }

        }

        return binding.root
    }

    private fun loginUser() {
        val email = binding.loginEmailField.text.toString()
        val password = binding.loginPasswordField.text.toString()

        mUserViewModel.loginUser(email, password)
    }

}
package com.example.noteappusingroomdatabase.ui.fragments.note

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteappusingroomdatabase.adapter.ListNoteAdapter
import com.example.noteappusingroomdatabase.ui.viewmodel.NoteViewModel
import com.example.noteappusingroomdatabase.R
import com.example.noteappusingroomdatabase.databinding.FragmentListNoteBinding
import com.example.noteappusingroomdatabase.ui.activity.LoginActivity
import com.example.noteappusingroomdatabase.ui.viewmodel.UserViewModel
import com.google.firebase.auth.FirebaseAuth

class ListNoteFragment : Fragment(){

    private lateinit var mNoteViewModel: NoteViewModel

    private lateinit var mUserViewModel: UserViewModel

    private val firebaseAuth = FirebaseAuth.getInstance()

    private var _binding: FragmentListNoteBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentListNoteBinding.inflate(inflater, container, false)

        binding.addFloatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listNoteFragment_to_noteFragment)
        }

        binding.bottomAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.camera -> {
                    true
                }

                R.id.account -> {
                    val intent = Intent(requireContext(), LoginActivity::class.java)
                    startActivity(intent)
                    true
                }

                else -> false
            }
        }


        val recyclerView = binding.recyclerView
        val adapter = ListNoteAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false)

        mNoteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]


        mUserViewModel.currentUser.observe(viewLifecycleOwner) { user ->
            Log.d("UserIdListNote", "Firebase LiveData: ${user?.uid}")

            mNoteViewModel.getNotesByUserId(user?.uid.toString()).observe(viewLifecycleOwner) { notes ->
                adapter.setData(notes)
            }
        }



        return binding.root
    }

}
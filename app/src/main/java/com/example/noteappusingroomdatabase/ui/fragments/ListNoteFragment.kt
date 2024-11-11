package com.example.noteappusingroomdatabase.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteappusingroomdatabase.adapter.ListNoteAdapter
import com.example.noteappusingroomdatabase.ui.viewmodel.NoteViewModel
import com.example.noteappusingroomdatabase.R
import com.example.noteappusingroomdatabase.databinding.FragmentListNoteBinding

class ListNoteFragment : Fragment(){

    private lateinit var mUserViewModel: NoteViewModel

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


        val recyclerView = binding.recyclerView
        val adapter = ListNoteAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false)

        mUserViewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        mUserViewModel.readAllDatabase.observe(viewLifecycleOwner, Observer { note->
            adapter.setData(note)
        })

        return binding.root
    }
}
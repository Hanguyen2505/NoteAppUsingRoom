package com.example.noteappusingroomdatabase.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteappusingroomdatabase.adapter.ListNoteAdapter
import com.example.noteappusingroomdatabase.ui.viewmodel.NoteViewModel
import com.example.noteappusingroomdatabase.R
import com.example.noteappusingroomdatabase.databinding.FragmentListNoteBinding
import com.example.noteappusingroomdatabase.ui.activity.SignUpActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListNoteFragment : Fragment(){

    private val mUserViewModel: NoteViewModel by viewModels()

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
                R.id.sort -> {
                    val intent = Intent(requireContext(), SignUpActivity::class.java)
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

        mUserViewModel.readAllDatabase.observe(viewLifecycleOwner, Observer { note->
            adapter.setData(note)
        })

        return binding.root
    }
}
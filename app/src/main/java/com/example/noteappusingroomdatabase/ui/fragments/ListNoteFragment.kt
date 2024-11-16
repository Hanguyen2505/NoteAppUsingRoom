package com.example.noteappusingroomdatabase.ui.fragments

<<<<<<< HEAD
import android.content.Intent
=======
>>>>>>> parent of 43a6b8b (new update)
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
<<<<<<< HEAD
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
=======
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
>>>>>>> parent of 43a6b8b (new update)
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteappusingroomdatabase.adapter.ListNoteAdapter
import com.example.noteappusingroomdatabase.ui.viewmodel.NoteViewModel
import com.example.noteappusingroomdatabase.R
import com.example.noteappusingroomdatabase.databinding.FragmentListNoteBinding
<<<<<<< HEAD
import com.example.noteappusingroomdatabase.ui.activity.SignUpActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListNoteFragment : Fragment(){

    private val mUserViewModel: NoteViewModel by viewModels()
=======

class ListNoteFragment : Fragment(){

    private lateinit var mUserViewModel: NoteViewModel
>>>>>>> parent of 43a6b8b (new update)

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

<<<<<<< HEAD
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
=======
>>>>>>> parent of 43a6b8b (new update)

        val recyclerView = binding.recyclerView
        val adapter = ListNoteAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false)

<<<<<<< HEAD
=======
        mUserViewModel = ViewModelProvider(this)[NoteViewModel::class.java]
>>>>>>> parent of 43a6b8b (new update)
        mUserViewModel.readAllDatabase.observe(viewLifecycleOwner, Observer { note->
            adapter.setData(note)
        })

        return binding.root
    }
}
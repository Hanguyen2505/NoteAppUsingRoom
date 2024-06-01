package com.example.noteappusingroomdatabase.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.noteappusingroomdatabase.NoteViewModel
import com.example.noteappusingroomdatabase.R
import com.example.noteappusingroomdatabase.databinding.FragmentNoteBinding
import com.example.noteappusingroomdatabase.roomdatabase.Note
import com.google.android.material.bottomsheet.BottomSheetBehavior


class NoteFragment : Fragment() {

    private lateinit var mNoteViewModel: NoteViewModel

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        _binding = FragmentNoteBinding.inflate(inflater, container, false)

        mNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        return binding.root

    }

    //button on click
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //button calls the bottom sheet fragment
        binding.moreVert.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragment()
            bottomSheetFragment.show(childFragmentManager, "BottomSheetDialog")
        }

        //button go back to the list note
        binding.textviewNotesBack.setOnClickListener {
            insertToDatabase()
            findNavController().navigate(R.id.action_noteFragment_to_listNoteFragment)

        }
    }

    //check if the title is not empty, then the note can be saved
    private fun insertToDatabase() {
        val title = _binding?.title?.text.toString()
        val subTitle = _binding?.subtitle?.text.toString()
        val noteInput = _binding?.noteInput?.text.toString()

        //check if the user
        if (inputCheck(title)) {
            val note = Note(0, title, subTitle, noteInput)
            mNoteViewModel.upsertNote(note)
        }
    }

    //check if the user type the note title
    private fun inputCheck (title: String): Boolean {
        return title.isNotEmpty()
    }


}
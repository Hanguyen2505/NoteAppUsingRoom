package com.example.noteappusingroomdatabase.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.noteappusingroomdatabase.NoteViewModel
import com.example.noteappusingroomdatabase.R
import com.example.noteappusingroomdatabase.databinding.FragmentUpdateBinding
import com.example.noteappusingroomdatabase.roomdatabase.Note

class UpdateFragment : Fragment() {

    private lateinit var mNoteViewModel: NoteViewModel

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)

        binding.title.setText(args.currentUser.title)
        binding.subtitle.setText(args.currentUser.subTitle)
        binding.noteInput.setText(args.currentUser.noteInput)

        mNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //go back to the ListNote
        binding.textviewNotesBack.setOnClickListener {
            updateItem()
            findNavController().navigate(R.id.action_updateFragment_to_listNoteFragment)
        }

        // button calls the bottom sheet fragment
        binding.moreVert.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragment()
            bottomSheetFragment.show(childFragmentManager, "BottomSheetFragment")
        }
    }

    private fun updateItem() {
        val title = binding.title.text.toString()
        val subTitle = binding.subtitle.text.toString()
        val noteInput = binding.noteInput.text.toString()

        if (inputCheck(title)) {
            val note = Note(args.currentUser.id, title, subTitle, noteInput)
            mNoteViewModel.upsertNote(note)
        }
    }

    private fun inputCheck(title: String): Boolean {
        return title.isNotEmpty()
    }


}
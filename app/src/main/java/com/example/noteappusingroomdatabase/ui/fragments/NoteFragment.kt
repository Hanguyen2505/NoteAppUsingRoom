package com.example.noteappusingroomdatabase.ui.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.noteappusingroomdatabase.BottomSheetListener
import com.example.noteappusingroomdatabase.R
import com.example.noteappusingroomdatabase.databinding.FragmentNoteBinding
import com.example.noteappusingroomdatabase.roomdatabase.Note
import com.example.noteappusingroomdatabase.ui.fragments.BottomSheetFragment
import com.example.noteappusingroomdatabase.ui.viewmodel.NoteViewModel
<<<<<<< HEAD:app/src/main/java/com/example/noteappusingroomdatabase/ui/fragments/note/NoteFragment.kt
<<<<<<< HEAD:app/src/main/java/com/example/noteappusingroomdatabase/ui/fragments/NoteFragment.kt
import dagger.hilt.android.AndroidEntryPoint
=======
import com.google.firebase.auth.FirebaseAuth
>>>>>>> redoNewBranch:app/src/main/java/com/example/noteappusingroomdatabase/ui/fragments/note/NoteFragment.kt
=======
>>>>>>> parent of 43a6b8b (new update):app/src/main/java/com/example/noteappusingroomdatabase/ui/fragments/NoteFragment.kt

@AndroidEntryPoint
class NoteFragment : Fragment(), BottomSheetListener {

    private lateinit var mNoteViewModel: NoteViewModel

    private var _binding: FragmentNoteBinding? = null

    private lateinit var noteTheme: String
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        noteTheme = "#A8565656"

        mNoteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]
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

        binding.viewSubtitleIndicator.setBackgroundColor(getColor(noteTheme))
    }

    //check if the title is not empty, then the note can be saved
    private fun insertToDatabase() {
        val title = _binding?.title?.text.toString()
        val subTitle = _binding?.subtitle?.text.toString()
        val noteInput = _binding?.noteInput?.text.toString()
        val noteColor = noteTheme

        //check if the user
        if (inputCheck(title)) {
<<<<<<< HEAD:app/src/main/java/com/example/noteappusingroomdatabase/ui/fragments/note/NoteFragment.kt
<<<<<<< HEAD:app/src/main/java/com/example/noteappusingroomdatabase/ui/fragments/NoteFragment.kt
//            val note = Note(0, title, subTitle, noteInput, noteColor)
//            mNoteViewModel.upsertNote(note)
=======
            val note = Note(0, firebaseAuth.currentUser!!.uid, title, subTitle, noteInput, noteColor)
=======
            val note = Note(0, title, subTitle, noteInput, noteColor)
>>>>>>> parent of 43a6b8b (new update):app/src/main/java/com/example/noteappusingroomdatabase/ui/fragments/NoteFragment.kt
            mNoteViewModel.upsertNote(note)
>>>>>>> redoNewBranch:app/src/main/java/com/example/noteappusingroomdatabase/ui/fragments/note/NoteFragment.kt
        }
    }

    //check if the user type the note title
    private fun inputCheck (title: String): Boolean {
        return title.isNotEmpty()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun getColor(colorString: String): Int {
        return Color.parseColor(colorString)

    }

    override fun onAddImageButtonClicked() {
        TODO("Not yet implemented")
    }

    override fun onDeleteButtonClicked() {

    }

    //*** change the color of viewSubtitleIndicator
    override fun onGrayButtonClicked() {
        binding.viewSubtitleIndicator.setBackgroundResource(R.drawable.gray_theme)
    }

    override fun onYellowButtonClicked() {
        noteTheme = "#A8565656"
        binding.viewSubtitleIndicator.setBackgroundResource(R.drawable.yellow_theme)
    }

    override fun onRedButtonClicked() {
        noteTheme = "#DAC936"
        binding.viewSubtitleIndicator.setBackgroundResource(R.drawable.red_theme)
    }

    override fun onBlueButtonClicked() {
        noteTheme = "#F44336"
        binding.viewSubtitleIndicator.setBackgroundResource(R.drawable.blue_theme)
    }

    override fun onBlackButtonClicked() {
        noteTheme = "#FF000000"
        binding.viewSubtitleIndicator.setBackgroundResource(R.drawable.black_theme)
    }

    override fun onWhiteButtonClicked() {
        noteTheme = "#FF000000"
        binding.viewSubtitleIndicator.setBackgroundResource(R.drawable.white_theme)
    }
    //*****************************


}
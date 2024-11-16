package com.example.noteappusingroomdatabase.ui.fragments

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.noteappusingroomdatabase.BottomSheetListener
import com.example.noteappusingroomdatabase.ui.viewmodel.NoteViewModel
import com.example.noteappusingroomdatabase.R
import com.example.noteappusingroomdatabase.SelectImage
import com.example.noteappusingroomdatabase.databinding.FragmentUpdateBinding
<<<<<<< HEAD:app/src/main/java/com/example/noteappusingroomdatabase/ui/fragments/note/UpdateFragment.kt
import com.example.noteappusingroomdatabase.roomdatabase.note.Note
<<<<<<< HEAD:app/src/main/java/com/example/noteappusingroomdatabase/ui/fragments/UpdateFragment.kt
import dagger.hilt.android.AndroidEntryPoint
=======
import com.google.firebase.auth.FirebaseAuth
>>>>>>> redoNewBranch:app/src/main/java/com/example/noteappusingroomdatabase/ui/fragments/note/UpdateFragment.kt
=======
import com.example.noteappusingroomdatabase.roomdatabase.Note
>>>>>>> parent of 43a6b8b (new update):app/src/main/java/com/example/noteappusingroomdatabase/ui/fragments/UpdateFragment.kt

@AndroidEntryPoint
class UpdateFragment : Fragment(), BottomSheetListener {

    private lateinit var mNoteViewModel: NoteViewModel

    private var _binding: FragmentUpdateBinding? = null
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    private lateinit var noteTheme: String

    private val binding get() = _binding!!
    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        init()

        mNoteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]

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

        binding.viewSubtitleIndicator.setBackgroundColor(getColor(noteTheme))

    }

    private fun updateItem() {
        val title = binding.title.text.toString()
        val subTitle = binding.subtitle.text.toString()
        val noteInput = binding.noteInput.text.toString()
        val noteColor = noteTheme

        if (inputCheck(title)) {
<<<<<<< HEAD:app/src/main/java/com/example/noteappusingroomdatabase/ui/fragments/note/UpdateFragment.kt
<<<<<<< HEAD:app/src/main/java/com/example/noteappusingroomdatabase/ui/fragments/UpdateFragment.kt
//            val note = Note(args.currentNote.id, title, subTitle, noteInput, noteColor)
//            mNoteViewModel.upsertNote(note)
=======
            val note = Note(args.currentNote.id, firebaseAuth.currentUser!!.uid, title, subTitle, noteInput, noteColor)
=======
            val note = Note(args.currentNote.id, title, subTitle, noteInput, noteColor)
>>>>>>> parent of 43a6b8b (new update):app/src/main/java/com/example/noteappusingroomdatabase/ui/fragments/UpdateFragment.kt
            mNoteViewModel.upsertNote(note)
>>>>>>> redoNewBranch:app/src/main/java/com/example/noteappusingroomdatabase/ui/fragments/note/UpdateFragment.kt
        }
    }

    private fun inputCheck(title: String): Boolean {
        return title.isNotEmpty()
    }

    private fun getColor(colorString: String): Int {
        return Color.parseColor(colorString)
    }

    override fun onAddImageButtonClicked() {
//        pickImage()
    }

    override fun onDeleteButtonClicked() {
        val title = binding.title.text.toString()
        val subTitle = binding.subtitle.text.toString()
        val noteInput = binding.noteInput.text.toString()

        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") {_, _ ->
<<<<<<< HEAD:app/src/main/java/com/example/noteappusingroomdatabase/ui/fragments/note/UpdateFragment.kt
<<<<<<< HEAD:app/src/main/java/com/example/noteappusingroomdatabase/ui/fragments/UpdateFragment.kt
//            val note = Note(args.currentNote.id, title, subTitle, noteInput, noteTheme)
//            mNoteViewModel.deleteNote(note)
=======
            val note = Note(args.currentNote.id, firebaseAuth.currentUser!!.uid, title, subTitle, noteInput, noteTheme)
=======
            val note = Note(args.currentNote.id, title, subTitle, noteInput, noteTheme)
>>>>>>> parent of 43a6b8b (new update):app/src/main/java/com/example/noteappusingroomdatabase/ui/fragments/UpdateFragment.kt
            mNoteViewModel.deleteNote(note)
>>>>>>> redoNewBranch:app/src/main/java/com/example/noteappusingroomdatabase/ui/fragments/note/UpdateFragment.kt
            findNavController().navigate(R.id.action_updateFragment_to_listNoteFragment)
            Toast.makeText(requireContext(), "Successfully deleted", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No") {_, _ ->

        }
        builder.setTitle("Do you want to delete ${args.currentNote.title}")
        builder.setMessage("Are you sure to DELETE ${args.currentNote.title}")
        builder.show()
    }

    private fun init() {
        binding.title.setText(args.currentNote.title)
        binding.subtitle.setText(args.currentNote.subTitle)
        binding.noteInput.setText(args.currentNote.noteInput)
        noteTheme = args.currentNote.noteColor

    }

    //*** change the color of viewSubtitleIndicator
    override fun onGrayButtonClicked() {
        noteTheme = "#A8565656"
        binding.viewSubtitleIndicator.setBackgroundResource(R.drawable.gray_theme)
    }

    override fun onYellowButtonClicked() {
        noteTheme = "#DAC936"
        binding.viewSubtitleIndicator.setBackgroundResource(R.drawable.yellow_theme)
    }

    override fun onRedButtonClicked() {
        noteTheme = "#F44336"
        binding.viewSubtitleIndicator.setBackgroundResource(R.drawable.red_theme)
    }

    override fun onBlueButtonClicked() {
        noteTheme = "#03A9F4"
        binding.viewSubtitleIndicator.setBackgroundResource(R.drawable.blue_theme)
    }

    override fun onBlackButtonClicked() {
        noteTheme = "#FF000000"
        binding.viewSubtitleIndicator.setBackgroundResource(R.drawable.black_theme)
    }

    override fun onWhiteButtonClicked() {
        noteTheme = "#FFFFFFFF"
        binding.viewSubtitleIndicator.setBackgroundResource(R.drawable.white_theme)
    }

//    override fun registerResult() {
//        resultLauncher = registerForActivityResult(
//            ActivityResultContracts.StartActivityForResult()
//        ) { result ->
//            if(result.resultCode == Activity.RESULT_OK) {
//                val data: Intent? = result.data
//                if (data != null) {
//                    val imageUri: Uri? = data.data
//                    uriString = imageUri.toString()
//                    binding.image.setImageURI(Uri.parse(uriString))
//                }
//            }
//        }
//    }

//    override fun pickImage() {
//        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//        resultLauncher.launch(intent)
//    }
    //*****************************


}
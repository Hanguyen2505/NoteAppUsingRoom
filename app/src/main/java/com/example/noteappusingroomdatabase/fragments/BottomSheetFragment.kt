package com.example.noteappusingroomdatabase.fragments

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.noteappusingroomdatabase.R
import com.example.noteappusingroomdatabase.databinding.FragmentBottomSheetBinding
import com.example.noteappusingroomdatabase.databinding.FragmentNoteBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentBottomSheetBinding? = null
    private val binding get() = _binding!!

    private val icDoneCode = R.drawable.ic_done

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBottomSheetBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //this key turn your note into gray
        binding.colorNoteGray.setOnClickListener{
            binding.iconDoneGray.setImageResource(icDoneCode)
            binding.iconDoneYellow.setImageResource(0)
            binding.iconDoneRed.setImageResource(0)
            binding.iconDoneBlue.setImageResource(0)
            binding.iconDoneBlack.setImageResource(0)
            binding.iconDoneWhite.setImageResource(0)
        }

        //this key turn your note into yellow
        binding.colorNoteYellow.setOnClickListener{
            binding.iconDoneGray.setImageResource(0)
            binding.iconDoneYellow.setImageResource(icDoneCode)
            binding.iconDoneRed.setImageResource(0)
            binding.iconDoneBlue.setImageResource(0)
            binding.iconDoneBlack.setImageResource(0)
            binding.iconDoneWhite.setImageResource(0)
        }

        //this key turn your note into red
        binding.colorNoteRed.setOnClickListener{
            binding.iconDoneGray.setImageResource(0)
            binding.iconDoneYellow.setImageResource(0)
            binding.iconDoneRed.setImageResource(icDoneCode)
            binding.iconDoneBlue.setImageResource(0)
            binding.iconDoneBlack.setImageResource(0)
            binding.iconDoneWhite.setImageResource(0)
        }

        //this key turn your note into blue
        binding.colorNoteBlue.setOnClickListener{
            binding.iconDoneGray.setImageResource(0)
            binding.iconDoneYellow.setImageResource(0)
            binding.iconDoneRed.setImageResource(0)
            binding.iconDoneBlue.setImageResource(icDoneCode)
            binding.iconDoneBlack.setImageResource(0)
            binding.iconDoneWhite.setImageResource(0)
        }

        //this key turn your note into black
        binding.colorNoteBlack.setOnClickListener{
            binding.iconDoneGray.setImageResource(0)
            binding.iconDoneYellow.setImageResource(0)
            binding.iconDoneRed.setImageResource(0)
            binding.iconDoneBlue.setImageResource(0)
            binding.iconDoneBlack.setImageResource(icDoneCode)
            binding.iconDoneWhite.setImageResource(0)
        }

        //this key turn your note into white
        binding.colorNoteWhite.setOnClickListener{
            binding.iconDoneGray.setImageResource(0)
            binding.iconDoneYellow.setImageResource(0)
            binding.iconDoneRed.setImageResource(0)
            binding.iconDoneBlue.setImageResource(0)
            binding.iconDoneBlack.setImageResource(0)
            binding.iconDoneWhite.setImageResource(icDoneCode)
        }
    }


}
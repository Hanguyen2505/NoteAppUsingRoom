package com.example.noteappusingroomdatabase.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.noteappusingroomdatabase.R
import com.example.noteappusingroomdatabase.roomdatabase.note.Note
import com.example.noteappusingroomdatabase.ui.fragments.note.ListNoteFragmentDirections

class ListNoteAdapter: RecyclerView.Adapter<ListNoteAdapter.MyViewHolder>() {

    private var listNote = emptyList<Note>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleCv: TextView = itemView.findViewById(R.id.title_card_view)
        val subtitleCv: TextView = itemView.findViewById(R.id.subtitle_card_view)
        val cardView: CardView = itemView.findViewById(R.id.note_card_view)
        val layoutCardView: ConstraintLayout = itemView.findViewById(R.id.layout_cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(
                    R.layout.note_cardview, parent, false
                )
        )
    }

    override fun getItemCount(): Int {
        return listNote.size
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = listNote[position]
        holder.titleCv.text = currentItem.title
        holder.subtitleCv.text = currentItem.subTitle

        val colorStringCode = currentItem.noteColor
        holder.layoutCardView.setBackgroundColor(parseColor(colorStringCode))

        //onclick recyclerView Item
        //TODO create, update the note color
        holder.cardView.setOnClickListener {
            val action = ListNoteFragmentDirections.actionListNoteFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(note: List<Note>) {
        this.listNote = note
        notifyDataSetChanged()
    }

    private fun parseColor(colorString: String): Int {
        return Color.parseColor(colorString)

    }

}
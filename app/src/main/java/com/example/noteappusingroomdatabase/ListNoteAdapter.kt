package com.example.noteappusingroomdatabase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.ListFragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.noteappusingroomdatabase.fragments.ListNoteFragment
import com.example.noteappusingroomdatabase.fragments.ListNoteFragmentDirections
import com.example.noteappusingroomdatabase.roomdatabase.Note

class ListNoteAdapter: RecyclerView.Adapter<ListNoteAdapter.MyViewHolder>() {

    private var listNote = emptyList<Note>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val titleCv: TextView = itemView.findViewById(R.id.title_card_view)
        val subtitleCv: TextView = itemView.findViewById(R.id.subtitle_card_view)
        val noteInputCv: TextView = itemView.findViewById(R.id.note_input_card_view)
        val cardView: CardView = itemView.findViewById(R.id.note_card_view)

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

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = listNote[position]
        holder.titleCv.text = currentItem.title
        holder.subtitleCv.text = currentItem.subTitle
        holder.noteInputCv.text = currentItem.noteInput

        //onclick recyclerView Item
        holder.cardView.setOnClickListener {
            val action = ListNoteFragmentDirections.actionListNoteFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(note: List<Note>) {
        this.listNote = note
        notifyDataSetChanged()
    }
}
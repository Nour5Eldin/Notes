package com.noureldin.notes.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.noureldin.notes.R
import com.noureldin.notes.databinding.ItemNoteBinding
import com.noureldin.notes.dataroom.model.Note


class NotesAdapter(private val context: Context?) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    var notesList = mutableListOf<Note>()

    fun setMyNotesList(list: MutableList<Note>) {
        notesList = list
        notifyDataSetChanged()
    }
    fun removeNoteFromList(index: Int){
        notesList.removeAt(0)
        notifyItemRemoved(index)

    }

    fun setListItem(note: Note, position: Int){
        notesList[position] = note
        notifyItemChanged(position)

    }
    class NotesViewHolder(val binding: ItemNoteBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note){
            binding.apply {
                title.text = note.title
                details.text = note.details
                date.text = note.date
            }
            if (note.isArchived){
                binding.archiveImv.setImageResource(R.drawable.unarcvied)
            }else{
                binding.archiveImv.setImageResource(R.drawable.ic_archive)
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NotesViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return notesList.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = notesList[position]
        holder.bind(note)
        archiveListener?.let { archiveListener->
            holder.binding.archiveImv.setOnClickListener {
                archiveListener.onClick(note,position)
            }

        }


        deleteListener?.let { deleteListener->
            holder.binding.deleteImv.setOnClickListener {
                deleteListener.onClick(position, note)
            }
        }

        when(note.priority){
            "1"->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.green_dot)
            }
            "2"->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.blue_dot)
            }
            "3"-> {
                holder.binding.viewPriority.setBackgroundResource(R.drawable.bluelight_dot)
            }
            "4"->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.purple_dot)
            }
            "5"->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.orange_dot)
            }
            "6"->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.grey_dot)
            }
            "7"->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.yellow_dot)
            }
            "8" ->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.peach_dot)
            }
            "9"->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.red_dot)
            }

        }
    }

    var archiveListener: OnArchiveClickListener? =null
    fun interface OnArchiveClickListener{
        fun onClick(note: Note, position: Int)
    }
    var deleteListener: OnDeleteClickListener? = null
    fun interface OnDeleteClickListener{
        fun onClick(position: Int, note: Note)
    }


}
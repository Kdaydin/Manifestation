package com.task.noteapp.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.task.noteapp.databinding.ItemNoteBinding
import com.task.noteapp.db.entities.NoteEntity

class NotesAdapter(private val actionListener: NoteActionListener) :
    RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    private val notes: ArrayList<NoteEntity> = arrayListOf()

    class ViewHolder(val binding: ItemNoteBinding, val listener: NoteActionListener) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: NoteEntity) {
            binding.note = note
            binding.btnDelete.setOnClickListener {
                listener.deleteNote(note)
            }
            binding.btnEdit.setOnClickListener {
                listener.editNote(note)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, actionListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(notes[holder.adapterPosition])
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun update(update: List<NoteEntity>) {
        val callback = NoteDiffCallback(notes, update)
        val diffResult = DiffUtil.calculateDiff(callback)
        notes.clear()
        notes.addAll(update)
        diffResult.dispatchUpdatesTo(this)
    }

    class NoteDiffCallback(
        private val oldNotes: List<NoteEntity>,
        private val newNotes: List<NoteEntity>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldNotes.size
        }

        override fun getNewListSize(): Int {
            return newNotes.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldNotes[oldItemPosition].createDate == newNotes[newItemPosition].createDate
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return (oldNotes[oldItemPosition].title == newNotes[newItemPosition].title)
                    && (oldNotes[oldItemPosition].description == newNotes[newItemPosition].description)
                    && (oldNotes[oldItemPosition].imgUrl == newNotes[newItemPosition].imgUrl)
                    && (oldNotes[oldItemPosition].isEdited == newNotes[newItemPosition].isEdited)
        }

    }
}
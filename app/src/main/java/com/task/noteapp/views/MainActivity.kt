package com.task.noteapp.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.task.noteapp.R
import com.task.noteapp.databinding.ActivityMainBinding
import com.task.noteapp.db.entities.NoteEntity
import com.task.noteapp.viewmodel.MainUIState
import com.task.noteapp.viewmodel.MainViewModel
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

@KoinApiExtension
class MainActivity : AppCompatActivity(), KoinComponent {

    var binding: ActivityMainBinding? = null

    private val viewModel: MainViewModel = get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding?.viewModel = viewModel
        val adapter = NotesAdapter(object : NoteActionListener {
            override fun editNote(noteEntity: NoteEntity) {
                startActivity(
                    Intent(
                        this@MainActivity,
                        EditNoteActivity::class.java
                    ).putExtra("note", noteEntity)
                )
            }

            override fun deleteNote(noteEntity: NoteEntity) {
                viewModel.deleteNote(noteEntity)
            }

        })
        viewModel.uiState.observe(this, Observer {
            when (it) {
                is MainUIState.EmptyList -> {
                    adapter.update(emptyList())

                }

                is MainUIState.NoteList -> {
                    adapter.update(viewModel.notes.value ?: emptyList())
                }

                is MainUIState.NewNote -> {
                    startActivity(
                        Intent(
                            this@MainActivity,
                            EditNoteActivity::class.java
                        )
                    )
                }
            }
        })
        binding?.rvNotes?.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllNotes()
    }
}
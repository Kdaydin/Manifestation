package com.task.noteapp.views

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.task.noteapp.R
import com.task.noteapp.databinding.ActivityEditBinding
import com.task.noteapp.db.entities.NoteEntity
import com.task.noteapp.viewmodel.EditNoteViewModel
import com.task.noteapp.viewmodel.EditUIState
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

@KoinApiExtension
class EditNoteActivity : AppCompatActivity(), KoinComponent {

    var binding: ActivityEditBinding? = null

    private val viewModel: EditNoteViewModel = get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit)
        binding?.viewModel = viewModel
        val note = intent.extras?.getSerializable("note") as? NoteEntity
        note?.let {
            viewModel.title.value = it.title
            viewModel.description.value = it.description
            viewModel.imgUrl.value = it.imgUrl
            viewModel.note = it
            binding?.executePendingBindings()
        }

        viewModel.uiState.observe(this, Observer {
            when (it) {
                is EditUIState.DataSaved -> {
                    Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show()
                    finish()
                }
                is EditUIState.Error -> {
                    Toast.makeText(this, "An Error Occured. Try Again!", Toast.LENGTH_SHORT)
                        .show()
                    finish()
                }
            }
        })
    }
}
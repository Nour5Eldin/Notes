package com.noureldin.notes.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.Toast
import androidx.activity.viewModels
import com.noureldin.notes.R
import com.noureldin.notes.databinding.ActivityCreateNotesBinding
import com.noureldin.notes.dataroom.model.Note
import com.noureldin.notes.desginpattern.viewmodel.NotesViewModel
import java.util.*


class CreateNotesActivity : AppCompatActivity() {
    lateinit var binding: ActivityCreateNotesBinding
    var priority: String="1"
    val viewModel: NotesViewModel by  viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCreateNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = resources.getString(R.string.create_new_note)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.pGreen.setImageResource(R.drawable.save)
        binding.pGreen.setOnClickListener {
            priority="1"
            binding.pGreen.setImageResource(R.drawable.save)
            binding.pBlue.setImageResource(0)
            binding.pPurple.setImageResource(0)
            binding.pGrey.setImageResource(0)
            binding.pOrange.setImageResource(0)
            binding.pRed.setImageResource(0)
            binding.pYellow.setImageResource(0)
            binding.pPeach.setImageResource(0)
        }
        binding.pBlue.setOnClickListener {
            priority="2"
            binding.pBlue.setImageResource(R.drawable.save)
            binding.pGreen.setImageResource(0)
            binding.pPurple.setImageResource(0)
            binding.pGrey.setImageResource(0)
            binding.pOrange.setImageResource(0)
            binding.pRed.setImageResource(0)
            binding.pYellow.setImageResource(0)
            binding.pPeach.setImageResource(0)

        }
        binding.pBlueLight.setOnClickListener {
            priority="3"
            binding.pBlueLight.setImageResource(R.drawable.save)
            binding.pBlue.setImageResource(0)
            binding.pGreen.setImageResource(0)
            binding.pPurple.setImageResource(0)
            binding.pGrey.setImageResource(0)
            binding.pOrange.setImageResource(0)
            binding.pRed.setImageResource(0)
            binding.pYellow.setImageResource(0)
            binding.pPeach.setImageResource(0)
        }
        binding.pPurple.setOnClickListener {
            priority="4"
            binding.pPurple.setImageResource(R.drawable.save)
            binding.pBlue.setImageResource(0)
            binding.pGreen.setImageResource(0)
            binding.pGrey.setImageResource(0)
            binding.pOrange.setImageResource(0)
            binding.pRed.setImageResource(0)
            binding.pYellow.setImageResource(0)
            binding.pPeach.setImageResource(0)
        }
        binding.pGrey.setOnClickListener {
            priority="5"
            binding.pGrey.setImageResource(R.drawable.save)
            binding.pGreen.setImageResource(0)
            binding.pBlue.setImageResource(0)
            binding.pPurple.setImageResource(0)
            binding.pOrange.setImageResource(0)
            binding.pRed.setImageResource(0)
            binding.pYellow.setImageResource(0)
            binding.pPeach.setImageResource(0)
        }
        binding.pOrange.setOnClickListener {
            priority="6"
            binding.pOrange.setImageResource(R.drawable.save)
            binding.pGreen.setImageResource(0)
            binding.pBlue.setImageResource(0)
            binding.pPurple.setImageResource(0)
            binding.pGrey.setImageResource(0)
            binding.pRed.setImageResource(0)
            binding.pYellow.setImageResource(0)
            binding.pPeach.setImageResource(0)
        }
        binding.pRed.setOnClickListener {
            priority="7"
            binding.pRed.setImageResource(R.drawable.save)
            binding.pGreen.setImageResource(0)
            binding.pBlue.setImageResource(0)
            binding.pPurple.setImageResource(0)
            binding.pGrey.setImageResource(0)
            binding.pOrange.setImageResource(0)
            binding.pYellow.setImageResource(0)
            binding.pPeach.setImageResource(0)
        }
        binding.pYellow.setOnClickListener {
            priority="8"
            binding.pYellow.setImageResource(R.drawable.save)
            binding.pGreen.setImageResource(0)
            binding.pBlue.setImageResource(0)
            binding.pPurple.setImageResource(0)
            binding.pRed.setImageResource(0)
            binding.pGrey.setImageResource(0)
            binding.pOrange.setImageResource(0)
            binding.pPeach.setImageResource(0)
        }
        binding.pPeach.setOnClickListener {
            priority="9"
            binding.pPeach.setImageResource(R.drawable.save)
            binding.pYellow.setImageResource(0)
            binding.pGreen.setImageResource(0)
            binding.pBlue.setImageResource(0)
            binding.pPurple.setImageResource(0)
            binding.pRed.setImageResource(0)
            binding.pGrey.setImageResource(0)
            binding.pOrange.setImageResource(0)
        }

        binding.btnCreateNote.setOnClickListener {
            createNote()
        }

    }

    private fun createNote() {
            val title = binding.edtNoteTitle.text.toString()
            val details = binding.edtNoteDetails.text.toString()
            val notesDate = getCurrentDate()

            if (validateTextFields(title,details)) {
                val note = Note(
                    title = title,
                    details = details,
                    date = notesDate,
                    isArchived = intent.getBooleanExtra("isArchived",false),
                    priority = priority
                )
                viewModel.addNotes(note)

//                notesList.add(note)
                finish()

            }else{
                Toast.makeText(this,"Please fill the required fields!", Toast.LENGTH_LONG).show()
            }




    }
    private fun validateTextFields(title:String,details:String):Boolean {
        return title.isNotEmpty() || details.isNotEmpty()
    }

    private fun getCurrentDate(): String {
        val date = Calendar.getInstance().time
        return DateFormat.format("dd MMMM, yyyy", date).toString()

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
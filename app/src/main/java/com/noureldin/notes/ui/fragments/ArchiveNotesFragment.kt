package com.noureldin.notes.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.noureldin.notes.R
import com.noureldin.notes.databinding.DialogDeleteBinding
import com.noureldin.notes.databinding.FragmentArchiveNotesBinding
import com.noureldin.notes.dataroom.model.Note
import com.noureldin.notes.ui.activities.CreateNotesActivity
import com.noureldin.notes.ui.adapter.NotesAdapter
import com.noureldin.notes.desginpattern.viewmodel.NotesViewModel

class ArchiveNotesFragment : Fragment() {
    lateinit var binding: FragmentArchiveNotesBinding
    lateinit var adapter: NotesAdapter
    private var notesList: List<Note> = emptyList()
    val viewModel: NotesViewModel by  viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArchiveNotesBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareRecyclerView()
        onAddNotesButtonClick()
    }
    override fun onStart() {
        super.onStart()
        adapter.setMyNotesList(notesList.filter { note -> note.isArchived }.toMutableList())
    }

    override fun onResume() {
        super.onResume()
        viewModel.getNotes()
        adapter.setMyNotesList(notesList.filter { note -> note.isArchived }.toMutableList())

    }

    private fun prepareRecyclerView() {
        adapter = NotesAdapter(context)
        binding.rvAllArchivedNotes.adapter = adapter
        viewModel.notesList.observe(viewLifecycleOwner) {
            notesList = it
            adapter.setMyNotesList(notesList.toMutableList())
        }
       // adapter.setMyNotesList(notesList.filter { note: Note -> note.isArchived }.toMutableList())

        binding.rvAllArchivedNotes.layoutManager = GridLayoutManager(requireContext(),2)

        onDeleteClick()
        onArchiveClick()
    }
    private fun onDeleteClick() {
        adapter.deleteListener = NotesAdapter.OnDeleteClickListener{ position, note ->
            displayDeleteBottomSheetDialog(position, note)
        }
    }
    private fun displayDeleteBottomSheetDialog(position:Int, note: Note) {
        val bottomSheetDialog = BottomSheetDialog(requireContext(),R.style.BottomSheetStyle)
        val binding = DialogDeleteBinding.inflate(requireActivity().layoutInflater)
        bottomSheetDialog.setContentView(binding.root)
        bottomSheetDialog.show()

        binding.noBtn.setOnClickListener {
                        bottomSheetDialog.dismiss()
        }

        binding.yesBtn.setOnClickListener {
            notesList.toMutableList().removeAt(position)
            notesList = notesList.toMutableList().filterIndexed { index, _ -> index != position }
            adapter.removeNoteFromList(position)
            viewModel.deleteNotes(note.id)
            bottomSheetDialog.dismiss()
        }
    }
    private fun onArchiveClick() {
        adapter.archiveListener = NotesAdapter.OnArchiveClickListener{ note: Note, position ->
            note.isArchived = false /////////////////
            adapter.setListItem(note,position)
            viewModel.updateNotes(note)
            adapter.setMyNotesList(notesList.filter { note -> note.isArchived }.toMutableList())

        }
    }

    private fun onAddNotesButtonClick() {
        viewModel.getNotes()
        binding.btnAddNotes.setOnClickListener {
            val intent = Intent(requireContext(), CreateNotesActivity::class.java)
            intent.putExtra("isArchived", true)
            startActivity(intent)
        }
    }

}
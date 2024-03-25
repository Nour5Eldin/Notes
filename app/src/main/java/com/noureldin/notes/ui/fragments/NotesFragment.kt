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
import com.noureldin.notes.databinding.FragmentNotesBinding
import com.noureldin.notes.dataroom.model.Note
import com.noureldin.notes.ui.activities.CreateNotesActivity
import com.noureldin.notes.ui.adapter.NotesAdapter
import com.noureldin.notes.desginpattern.viewmodel.NotesViewModel


class NotesFragment : Fragment() {
    lateinit var binding: FragmentNotesBinding
    lateinit var adapter: NotesAdapter
    private var notesList: List<Note> = emptyList()
    private val viewModel: NotesViewModel by  viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareRecyclerView()
        onAddNoteButtonClick()

    }
    private fun prepareRecyclerView() {
        adapter = NotesAdapter(context)
        binding.rvAllNotes.adapter = adapter
        viewModel.notesList.observe(viewLifecycleOwner) {
            notesList = it
            adapter.setMyNotesList(notesList.toMutableList())
        }
        binding.rvAllNotes.layoutManager =
            GridLayoutManager(requireContext(),2)
        onArchiveClick()
        onDeleteClick()
    }
    private fun onArchiveClick() {
        adapter.archiveListener = NotesAdapter.OnArchiveClickListener{note, position ->
            note.isArchived = true
            viewModel.updateNotes(note)
            adapter.setMyNotesList(notesList.filter { note -> !note.isArchived }.toMutableList())

        }
    }
    private fun onDeleteClick() {
        adapter.deleteListener = NotesAdapter.OnDeleteClickListener{  position, note ->
            displayDeleteBottomSheetDialog(position, note)
        }
    }
    private fun displayDeleteBottomSheetDialog(position:Int, note: Note) {
        val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetStyle)
        val binding = DialogDeleteBinding.inflate(requireActivity().layoutInflater)
        bottomSheetDialog.setContentView(binding.root)
        bottomSheetDialog.show()
        binding.noBtn.setOnClickListener {
            bottomSheetDialog.dismiss()
        }

        binding.yesBtn.setOnClickListener {
            viewModel.deleteNotes(note.id)
            notesList = notesList.toMutableList().filterIndexed { index, _ -> index != position }
            adapter.removeNoteFromList(position)
            bottomSheetDialog.dismiss()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getNotes()
        adapter.setMyNotesList(notesList.filter { note -> !note.isArchived }.toMutableList())

    }


    private fun onAddNoteButtonClick() {
//        viewModel.getNotes()
        binding.btnAddNotes.setOnClickListener {
            val intent =  Intent(requireContext(), CreateNotesActivity::class.java)
            intent.putExtra("isArchived",false)
            startActivity(intent)
        }

    }
}
package com.noureldin.notes.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.noureldin.notes.R
import com.noureldin.notes.databinding.ActivityMainBinding
import com.noureldin.notes.ui.fragments.ArchiveNotesFragment
import com.noureldin.notes.ui.fragments.NotesFragment
import com.noureldin.notes.desginpattern.viewmodel.NotesViewModel


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val viewModel: NotesViewModel by  viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getNotes()
        supportActionBar?.title = resources.getString(R.string.app_name)
        pushFragment(NotesFragment())

        binding.bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.notes_menu_item ->
                    pushFragment(NotesFragment())

                R.id.archived_notes_menu_item ->
                    pushFragment(ArchiveNotesFragment())
            }
            true
        }

    }
    private fun pushFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}
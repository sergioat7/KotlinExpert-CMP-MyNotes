package com.aragones.sergio.kotlinexpert.ui.screens.detail

import androidx.compose.runtime.mutableStateOf
import com.aragones.sergio.kotlinexpert.data.Note
import com.aragones.sergio.kotlinexpert.data.remote.NotesRepository
import com.aragones.sergio.kotlinexpert.update
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class DetailViewModel(private val scope: CoroutineScope, private val noteId: Long) {

    var state = mutableStateOf(UiState())
        private set

    init {
        if (noteId != Note.NEW_NOTE_ID) {
            loadNote()
        }
    }

    fun save() = scope.launch {

        state.update { it.copy(loading = true) }
        val note = state.value.note
        if (note.id == Note.NEW_NOTE_ID) {
            NotesRepository.save(note)
        } else {
            NotesRepository.update(note)
        }
        state.update { it.copy(saved = true) }
    }

    fun update(note: Note) {
        state.update { it.copy(note = note) }
    }

    fun delete() = scope.launch {

        state.update { it.copy(loading = true) }
        NotesRepository.delete(state.value.note)
        state.update { it.copy(saved = true) }
    }

    private fun loadNote() = scope.launch {

        state.update { UiState(loading = true) }
        val note = NotesRepository.getById(noteId)
        state.update { UiState(note = note) }
    }

    data class UiState(
        val note: Note = Note(title = "", description = "", type = Note.Type.TEXT),
        val loading: Boolean = false,
        val saved: Boolean = false
    )
}
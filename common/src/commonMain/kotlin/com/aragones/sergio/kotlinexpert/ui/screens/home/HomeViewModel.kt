package com.aragones.sergio.kotlinexpert.ui.screens.home

import androidx.compose.runtime.mutableStateOf
import com.aragones.sergio.kotlinexpert.data.Note
import com.aragones.sergio.kotlinexpert.data.remote.NotesRepository
import com.aragones.sergio.kotlinexpert.update
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class HomeViewModel(private val scope: CoroutineScope) {

    var state = mutableStateOf(UiState())
        private set

    init {
        loadNotes()
    }

    private fun loadNotes() = scope.launch {

        state.update { UiState(loading = true) }
        val notes = NotesRepository.getAll()
        state.update { UiState(notes = notes) }
    }

    fun onFilterClicked(filter: Filter) {
        state.update { it.copy(filter = filter) }
    }

    fun deleteAll() = scope.launch {

        state.update { UiState(loading = true) }
        NotesRepository.deleteAll()
        state.update { UiState(notes = emptyList()) }
    }

    data class UiState(
        val notes: List<Note>? = null,
        val loading: Boolean = false,
        val filter: Filter = Filter.All
    ) {

        val filteredNotes: List<Note>?
            get() = when (filter) {
                Filter.All -> notes
                is Filter.ByType -> notes?.filter { it.type == filter.type }
            }
    }
}
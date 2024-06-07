package com.aragones.sergio.kotlinexpert.ui.screens.home

import com.aragones.sergio.kotlinexpert.data.Note
import com.aragones.sergio.kotlinexpert.data.fakeNotes
import com.aragones.sergio.kotlinexpert.update
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

object HomeState {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state

    suspend fun loadNotes() = withContext(Dispatchers.IO) {

        _state.update { it.copy(loading = true) }
        Note.fakeNotes.collect { notes ->
            _state.update { it.copy(notes = notes) }
        }
    }

    fun onFilterClicked(filter: Filter) {
        _state.update { it.copy(filter = filter) }
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
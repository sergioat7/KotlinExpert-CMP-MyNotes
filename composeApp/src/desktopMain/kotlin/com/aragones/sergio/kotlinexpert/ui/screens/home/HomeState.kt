package com.aragones.sergio.kotlinexpert.ui.screens.home

import androidx.compose.runtime.mutableStateOf
import com.aragones.sergio.kotlinexpert.data.Note

object HomeState {
    val notes = mutableStateOf(getNotes())

    private fun getNotes(): List<Note> = (1..10).map {
        Note(
            "Title $it",
            "Description $it",
            if (it % 3 == 0) Note.Type.AUDIO else Note.Type.TEXT
        )
    }
}
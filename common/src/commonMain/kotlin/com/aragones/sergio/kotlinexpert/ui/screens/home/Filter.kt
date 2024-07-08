package com.aragones.sergio.kotlinexpert.ui.screens.home

import com.aragones.sergio.kotlinexpert.data.Note

sealed interface Filter {
    data object All : Filter
    class ByType(val type: Note.Type) : Filter
}
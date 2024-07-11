package com.aragones.sergio.kotlinexpert.data

sealed interface Filter {
    data object All : Filter
    class ByType(val type: Note.Type) : Filter
}
package com.aragones.sergio.kotlinexpert.ui.screens.home

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun HomeScreen() = with(HomeState) {

    MaterialTheme {
        NoteList(notes.value)
    }
}
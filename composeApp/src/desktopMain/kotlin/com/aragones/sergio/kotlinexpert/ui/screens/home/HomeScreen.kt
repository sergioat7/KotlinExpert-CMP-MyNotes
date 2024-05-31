package com.aragones.sergio.kotlinexpert.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun HomeScreen() = with(HomeState) {

    val state = state.collectAsState().value

    LaunchedEffect(true) {
        loadNotes()
    }

    MaterialTheme {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {

            if (state.loading) {
                CircularProgressIndicator()
            }

            state.notes?.let {
                NoteList(it)
            }
        }
    }
}
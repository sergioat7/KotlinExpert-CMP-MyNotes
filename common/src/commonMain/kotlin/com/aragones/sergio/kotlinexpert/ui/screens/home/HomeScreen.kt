package com.aragones.sergio.kotlinexpert.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.aragones.sergio.kotlinexpert.data.Note
import com.aragones.sergio.kotlinexpert.getAppTitle
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun HomeScreen(viewModel: HomeViewModel, onNoteClick: (Long) -> Unit) {

    MaterialTheme {
        Scaffold(
            topBar = {
                TopBar(viewModel::onFilterClicked, viewModel::deleteAll)
            },
            floatingActionButton = {
                FloatingActionButton(onClick = { onNoteClick(Note.NEW_NOTE_ID) }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                }
            }
        ) { padding ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize().padding(padding)
            ) {

                if (viewModel.state.value.loading) {
                    CircularProgressIndicator()
                }

                viewModel.state.value.filteredNotes?.let { notes ->
                    NoteList(notes = notes, onNoteClick = { onNoteClick(it.id) })
                }
            }
        }
    }
}

@Composable
fun TopBar(onFilterClicked: (Filter) -> Unit, onDeleteAll: () -> Unit) {

    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text(getAppTitle()) },
        actions = {
            IconButton(onClick = { expanded = true }) {
                Icon(
                    imageVector = Icons.Default.FilterList,
                    contentDescription = "Filter"
                )
                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {

                    val items = listOf(
                        Filter.All to "All",
                        Filter.ByType(Note.Type.TEXT) to "Text",
                        Filter.ByType(Note.Type.AUDIO) to "Audio"
                    )
                    for ((filter, text) in items) {

                        DropdownMenuItem(onClick = {
                            expanded = false
                            onFilterClicked(filter)
                        }) {
                            Text(text)
                        }
                    }
                }
            }
            IconButton(onClick = onDeleteAll) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
            }
        }
    )
}
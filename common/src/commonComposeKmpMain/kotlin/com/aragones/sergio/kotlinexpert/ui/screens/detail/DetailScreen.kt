package com.aragones.sergio.kotlinexpert.ui.screens.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aragones.sergio.kotlinexpert.data.Note

@Composable
actual fun DetailScreen(viewModel: DetailViewModel, onClose: () -> Unit) {

    val note = viewModel.state.value.note

    Scaffold(
        topBar = {
            DetailTopAppBar(
                note = note,
                onClose = onClose,
                onSave = viewModel::save,
                onDelete = viewModel::delete,
            )
        },
    ) { padding ->

        Box(modifier = Modifier.padding(padding)) {
            if (viewModel.state.value.saved) {
                onClose()
            }

            if (viewModel.state.value.loading) {
                CircularProgressIndicator()
            } else {

                Column(modifier = Modifier.padding(32.dp)) {
                    OutlinedTextField(
                        value = note.title,
                        onValueChange = { viewModel.update(note.copy(title = it)) },
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Title") },
                        maxLines = 1
                    )
                    TypeDropdown(
                        value = note.type,
                        modifier = Modifier.fillMaxWidth().padding(vertical = 24.dp),
                        onValueChange = {
                            viewModel.update(note.copy(type = it))
                        }
                    )
                    OutlinedTextField(
                        value = note.description,
                        onValueChange = { viewModel.update(note.copy(description = it)) },
                        modifier = Modifier.fillMaxWidth().weight(1f),
                        label = { Text("Description") },
                    )
                }
            }
        }
    }
}

@Composable
private fun TypeDropdown(value: Note.Type, modifier: Modifier = Modifier, onValueChange: (Note.Type) -> Unit) {

    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier, propagateMinConstraints = true) {
        OutlinedTextField(value = value.toString(), onValueChange = {}, readOnly = true, trailingIcon = {
            IconButton(onClick = { expanded = true }) {
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
            }
        })
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            Note.Type.entries.forEach {
                DropdownMenuItem(
                    text = { Text(it.name) },
                    onClick = { onValueChange(it); expanded = false }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailTopAppBar(
    note: Note, onClose: () -> Unit, onSave: () -> Unit, onDelete: () -> Unit
) {
    TopAppBar(title = {
        Text(text = note.title)
    }, navigationIcon = {
        IconButton(onClick = onClose) {
            Icon(imageVector = Icons.Default.Close, contentDescription = null)
        }
    }, actions = {
        IconButton(onClick = onSave) {
            Icon(imageVector = Icons.Default.Save, contentDescription = null)
        }
        if (note.id != Note.NEW_NOTE_ID) {
            IconButton(onClick = onDelete) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
            }
        }
    })
}
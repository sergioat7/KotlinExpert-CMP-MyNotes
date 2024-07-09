package com.aragones.sergio.kotlinexpert.ui.screens.home

import androidx.compose.runtime.Composable
import com.aragones.sergio.kotlinexpert.data.Note
import com.aragones.sergio.kotlinexpert.ui.theme.AppStyleSheet
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text

@Composable
fun HomeScreen(viewModel: HomeViewModel, onNoteClick: (Long) -> Unit) {

    Div(attrs = { classes(AppStyleSheet.home) }

    ) {
        TopBar(onFilterClick = viewModel::onFilterClicked)

        Div {

            if (viewModel.state.value.loading) {
                Text("Loading...")
            }

            viewModel.state.value.filteredNotes?.let { notes ->
                NotesList(
                    notes = notes,
                    onNoteClick = { onNoteClick(it.id) }
                )
            }
        }

        Div(
            attrs = {
                classes(AppStyleSheet.fab)
                onClick { onNoteClick(Note.NEW_NOTE_ID) }
            }
        ) {
            Text("+")
        }
    }
}
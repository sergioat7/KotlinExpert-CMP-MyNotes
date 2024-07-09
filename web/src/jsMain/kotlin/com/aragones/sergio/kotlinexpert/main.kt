package com.aragones.sergio.kotlinexpert

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.aragones.sergio.kotlinexpert.data.Note
import com.aragones.sergio.kotlinexpert.ui.screens.home.HomeViewModel
import com.aragones.sergio.kotlinexpert.ui.theme.AppStyleSheet
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.css.marginLeft
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposable

fun main() {

    renderComposable(rootElementId = "root") {
        Style(AppStyleSheet)
        val scope = rememberCoroutineScope()
        val homeViewModel = remember { HomeViewModel(scope) }

        NotesList(homeViewModel.state.value.filteredNotes ?: emptyList()) { note ->
            //TODO:
        }
    }
}

@Composable
fun NotesList(notes: List<Note>, onNoteClick: (Note) -> Unit) {
    Div(attrs = { classes(AppStyleSheet.notesList) }) {
        notes.forEach { note ->
            NoteCard(note, onNoteClick)
        }
    }
}

@Composable
fun NoteCard(note: Note, onNoteClick: (Note) -> Unit) {
    Div(attrs = {
        onClick { onNoteClick(note) }
        classes(AppStyleSheet.noteCard)
    }) {
        Div(attrs = { classes(AppStyleSheet.noteCardHeader) }) {
            H3(attrs = { classes(AppStyleSheet.noteCardTitle) }) {
                Text(note.title)
            }

            if (note.type == Note.Type.AUDIO) {
                Span(attrs = { style { marginLeft(8.px) } }) {
                    Text("🎤")
                }
            }
        }

        Div {
            P {
                Text(note.description)
            }
        }
    }
}
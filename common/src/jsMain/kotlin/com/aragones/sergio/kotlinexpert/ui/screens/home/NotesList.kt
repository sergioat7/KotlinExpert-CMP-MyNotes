package com.aragones.sergio.kotlinexpert.ui.screens.home

import androidx.compose.runtime.Composable
import com.aragones.sergio.kotlinexpert.data.Note
import com.aragones.sergio.kotlinexpert.ui.theme.AppStyleSheet
import org.jetbrains.compose.web.css.marginLeft
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.*

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
package com.aragones.sergio.kotlinexpert

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.aragones.sergio.kotlinexpert.data.Note
import com.aragones.sergio.kotlinexpert.ui.screens.home.HomeViewModel
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposable

fun main() {

    renderComposable(rootElementId = "root") {
        val scope = rememberCoroutineScope()
        val homeViewModel = remember { HomeViewModel(scope) }

        NotesList(homeViewModel.state.value.filteredNotes ?: emptyList()) { note ->
            //TODO:
        }
    }
}

@Composable
fun NotesList(notes: List<Note>, onNoteClick: (Note) -> Unit) {
    Div(
        attrs = {
            style {
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Column)
                alignItems(AlignItems.Center)
                width(100.percent)
                height(100.percent)
            }
        },

        ) {
        notes.forEach { note ->
            NoteCard(note, onNoteClick)
        }
    }
}

@Composable
fun NoteCard(note: Note, onNoteClick: (Note) -> Unit) {
    Div(
        attrs = {
            onClick { onNoteClick(note) }

            style {
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Column)
                width(80.percent)
                maxWidth(600.px)
                marginTop(8.px)
                marginBottom(8.px)
                border(1.px, LineStyle.Solid, Color.black)
                borderRadius(4.px)
                padding(16.px)
                cursor("pointer")
            }
        }
    ) {
        Div(
            attrs = {
                style {
                    display(DisplayStyle.Flex)
                    flexDirection(FlexDirection.Row)
                    alignItems(AlignItems.Center)
                    width(100.percent)
                }
            }
        ) {
            H3(
                attrs = {
                    style {
                        flex(1)
                    }
                }
            ) {
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
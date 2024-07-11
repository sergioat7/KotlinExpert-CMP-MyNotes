package com.aragones.sergio.kotlinexpert.ui.screens.detail

import androidx.compose.runtime.Composable
import com.aragones.sergio.kotlinexpert.data.Note
import com.aragones.sergio.kotlinexpert.ui.common.Icon
import com.aragones.sergio.kotlinexpert.ui.theme.AppStyleSheet
import org.jetbrains.compose.web.attributes.placeholder
import org.jetbrains.compose.web.attributes.selected
import org.jetbrains.compose.web.dom.*

@Composable
actual fun DetailScreen(viewModel: DetailViewModel, onClose: () -> Unit) {

    val note = viewModel.state.value.note

    Div {
        TopBar(
            note = note,
            onClose = onClose,
            onSave = viewModel::save,
            onDelete = viewModel::delete
        )

        if (viewModel.state.value.saved) {
            onClose()
        }

        if (viewModel.state.value.loading) {
            Text("Loading...")
        } else {
            Div(attrs = { classes(AppStyleSheet.detail) }) {
                TextInput(
                    value = note.title,
                    attrs = {
                        classes(AppStyleSheet.detailInput)
                        placeholder("Title")
                        onInput { viewModel.update(note.copy(title = it.value)) }
                    }
                )
                TypeDropdown(
                    value = note.type,
                    onValueChange = { viewModel.update(note.copy(type = it)) }
                )
                TextArea(
                    value = note.description,
                    attrs = {
                        classes(AppStyleSheet.detailInput)
                        placeholder("Description")
                        onInput { viewModel.update(note.copy(description = it.value)) }
                    }
                )
            }
        }
    }
}

@Composable
private fun TypeDropdown(value: Note.Type, onValueChange: (Note.Type) -> Unit) {
    Select(
        attrs = {
            classes(AppStyleSheet.detailInput)
            onChange { onValueChange(Note.Type.valueOf(it.target.value)) }
        }
    ) {
        Note.Type.entries.forEach {
            Option(
                value = it.name,
                attrs = {
                    if (it == value) {
                        selected()
                    }
                }) {
                Text(it.name)
            }
        }
    }
}

@Composable
private fun TopBar(note: Note, onClose: () -> Unit, onSave: () -> Unit, onDelete: () -> Unit) {
    Div(attrs = { classes(AppStyleSheet.topBar) }) {
        Icon(
            iconName = "arrow_back",
            attrs = {
                classes(AppStyleSheet.topBarIcon)
                onClick { onClose() }
            }
        )

        H1(attrs = { classes(AppStyleSheet.topBarTitle) }) {
            Text(note.title)
        }

        Icon(
            iconName = "save",
            attrs = {
                classes(AppStyleSheet.topBarIcon)
                onClick { onSave() }
            }
        )

        if (note.id != Note.NEW_NOTE_ID) {
            Icon(
                iconName = "delete",
                attrs = {
                    classes(AppStyleSheet.topBarIcon)
                    onClick { onDelete() }
                }
            )
        }
    }
}
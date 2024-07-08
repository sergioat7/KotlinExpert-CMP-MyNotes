package com.aragones.sergio.kotlinexpert

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.aragones.sergio.kotlinexpert.ui.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "MyNotes",
    ) {
        App()
    }
}
package com.aragones.sergio.kotlinexpert

import com.aragones.sergio.kotlinexpert.ui.App
import com.aragones.sergio.kotlinexpert.ui.theme.AppStyleSheet
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.renderComposable

fun main() {

    renderComposable(rootElementId = "root") {
        Style(AppStyleSheet)
        App()
    }
}
package com.aragones.sergio.kotlinexpert

import com.aragones.sergio.kotlinexpert.ui.App
import com.aragones.sergio.kotlinexpert.ui.theme.AppStyleSheet
import kotlinx.browser.document
import kotlinx.browser.window
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.renderComposable

fun main() {

    composeSample()
    kotlinJsSample()
}

private fun composeSample() {

    document.getElementById("root") ?: return
    renderComposable(rootElementId = "root") {
        Style(AppStyleSheet)
        App()
    }
}

private fun kotlinJsSample() {

    window.onload = {
        val title = document.getElementById("title")
        if (title != null) {
            title.textContent = getAppTitle()
        }
    }
}
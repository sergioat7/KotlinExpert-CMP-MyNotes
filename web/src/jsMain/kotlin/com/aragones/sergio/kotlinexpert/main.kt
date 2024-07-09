package com.aragones.sergio.kotlinexpert

import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.aragones.sergio.kotlinexpert.ui.screens.home.HomeScreen
import com.aragones.sergio.kotlinexpert.ui.screens.home.HomeViewModel
import com.aragones.sergio.kotlinexpert.ui.theme.AppStyleSheet
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.renderComposable

fun main() {

    renderComposable(rootElementId = "root") {
        Style(AppStyleSheet)
        val scope = rememberCoroutineScope()
        val homeViewModel = remember { HomeViewModel(scope) }
        HomeScreen(homeViewModel, onNoteClick = { /*TODO*/ })
    }
}
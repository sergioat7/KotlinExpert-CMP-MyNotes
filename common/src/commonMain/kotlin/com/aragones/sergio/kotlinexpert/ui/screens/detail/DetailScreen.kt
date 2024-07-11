package com.aragones.sergio.kotlinexpert.ui.screens.detail

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

data class Detail(val noteId: Long) : Screen {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        DetailScreen(
            viewModel = rememberScreenModel { DetailViewModel(noteId) },
            onClose = { navigator.pop() }
        )
    }
}

@Composable
expect fun DetailScreen(viewModel: DetailViewModel, onClose: () -> Unit)
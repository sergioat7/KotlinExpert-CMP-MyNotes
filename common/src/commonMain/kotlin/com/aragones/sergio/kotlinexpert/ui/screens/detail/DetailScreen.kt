package com.aragones.sergio.kotlinexpert.ui.screens.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

data class Detail(val noteId: Long) : Screen {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        DetailScreen(
            viewModel = DetailViewModel(rememberCoroutineScope(), noteId),
            onClose = { navigator.pop() }
        )
    }
}

@Composable
expect fun DetailScreen(viewModel: DetailViewModel, onClose: () -> Unit)
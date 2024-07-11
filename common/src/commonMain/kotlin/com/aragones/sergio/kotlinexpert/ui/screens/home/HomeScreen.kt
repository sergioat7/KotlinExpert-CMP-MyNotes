package com.aragones.sergio.kotlinexpert.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.aragones.sergio.kotlinexpert.ui.screens.detail.Detail

object Home : Screen {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        HomeScreen(
            viewModel = HomeViewModel(rememberCoroutineScope()),
            onNoteClick = { navigator.push(Detail(it)) }
        )
    }
}

@Composable
expect fun HomeScreen(viewModel: HomeViewModel, onNoteClick: (Long) -> Unit)
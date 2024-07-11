package com.aragones.sergio.kotlinexpert.ui.screens.home

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.aragones.sergio.kotlinexpert.ui.screens.detail.Detail

object Home : Screen {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        HomeScreen(
            viewModel = rememberScreenModel { HomeViewModel() },
            onNoteClick = { navigator.push(Detail(it)) }
        )
    }
}

@Composable
expect fun HomeScreen(viewModel: HomeViewModel, onNoteClick: (Long) -> Unit)
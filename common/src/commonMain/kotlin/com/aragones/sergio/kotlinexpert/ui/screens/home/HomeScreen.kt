package com.aragones.sergio.kotlinexpert.ui.screens.home

import androidx.compose.runtime.Composable

@Composable
expect fun HomeScreen(viewModel: HomeViewModel, onNoteClick: (Long) -> Unit)
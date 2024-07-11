package com.aragones.sergio.kotlinexpert.ui.screens.detail

import androidx.compose.runtime.Composable

@Composable
expect fun DetailScreen(viewModel: DetailViewModel, onClose: () -> Unit)
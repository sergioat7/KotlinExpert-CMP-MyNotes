package com.aragones.sergio.kotlinexpert.ui

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.aragones.sergio.kotlinexpert.ui.screens.home.Home
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    Navigator(Home)
}


package com.aragones.sergio.kotlinexpert.ui

import androidx.compose.runtime.*
import com.aragones.sergio.kotlinexpert.ui.screens.detail.DetailScreen
import com.aragones.sergio.kotlinexpert.ui.screens.home.HomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

sealed interface Route {
    data object Home : Route
    data class Detail(val id: Long) : Route
}

@Composable
@Preview
fun App() {

    var route: Route by remember { mutableStateOf(Route.Home) }

    route.let {
        when (it) {
            Route.Home -> HomeScreen(onAddClick = { route = Route.Detail(-1) })
            is Route.Detail -> DetailScreen(it.id, onClose = { route = Route.Home })
        }
    }
}


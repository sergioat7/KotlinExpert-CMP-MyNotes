package com.aragones.sergio.kotlinexpert.ui.screens.home

import androidx.compose.runtime.*
import com.aragones.sergio.kotlinexpert.data.Note
import com.aragones.sergio.kotlinexpert.getAppTitle
import com.aragones.sergio.kotlinexpert.ui.common.Icon
import com.aragones.sergio.kotlinexpert.ui.theme.AppStyleSheet
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Text

@Composable
fun TopBar(onFilterClick: (Filter) -> Unit) {
    Div(attrs = { classes(AppStyleSheet.topBar) }) {
        H1(attrs = { classes(AppStyleSheet.topBarTitle) }) {
            Text(getAppTitle())
        }
        FiltersAction(onFilterClick)
    }
}

@Composable
fun FiltersAction(onFilterClick: (Filter) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Div {
        Icon(
            iconName = "filter_list",
            attrs = {
                classes(AppStyleSheet.topBarIcon)
                onClick { expanded = !expanded }
            })

        if (expanded) {
            Div(attrs = { classes(AppStyleSheet.filtersActionExpanded) }) {
                listOf(
                    Filter.All to "All",
                    Filter.ByType(Note.Type.TEXT) to "Text",
                    Filter.ByType(Note.Type.AUDIO) to "Audio"
                ).forEach { (filter, text) ->
                    Div(attrs = {
                        classes(AppStyleSheet.filtersActionExpandedItem)
                        onClick {
                            onFilterClick(filter)
                            expanded = false
                        }
                    }) {
                        Text(text)
                    }
                }
            }
        }
    }
}
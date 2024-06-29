package com.aragones.sergio.kotlinexpert.data

import kotlinx.serialization.Serializable

@Serializable
data class Note(val id: Long = NEW_NOTE_ID, val title: String, val description: String, val type: Type) {
    enum class Type { TEXT, AUDIO }
    companion object {
        const val NEW_NOTE_ID = -1L
    }
}
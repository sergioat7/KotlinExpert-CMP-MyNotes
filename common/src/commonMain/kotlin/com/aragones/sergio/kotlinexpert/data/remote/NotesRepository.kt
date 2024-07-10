package com.aragones.sergio.kotlinexpert.data.remote

import com.aragones.sergio.kotlinexpert.data.Note
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

expect val NOTES_URL: String

object NotesRepository {

    suspend fun save(note: Note) {

        notesClient.post(NOTES_URL) {
            setBody(note)
            contentType(ContentType.Application.Json)
        }
    }

    suspend fun getAll(): List<Note> {

        val response = notesClient.request(NOTES_URL)
        return response.body<List<Note>>()
    }

    suspend fun getById(id: Long): Note {

        val response = notesClient.request("$NOTES_URL/$id")
        return response.body()
    }

    suspend fun update(note: Note) {

        notesClient.put(NOTES_URL) {
            setBody(note)
            contentType(ContentType.Application.Json)
        }
    }

    suspend fun deleteAll() = notesClient.delete(NOTES_URL)

    suspend fun delete(note: Note) =
        notesClient.delete("$NOTES_URL/${note.id}")
}
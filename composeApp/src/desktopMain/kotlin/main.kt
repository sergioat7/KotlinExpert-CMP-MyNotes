import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

class AppState {
    val notes = mutableStateOf(getNotes())

    private fun getNotes(): List<Note> = (1..10).map {
        Note(
            "Title $it",
            "Description $it",
            if (it % 3 == 0) Note.Type.AUDIO else Note.Type.TEXT
        )
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "MyNotes",
    ) {
        App(AppState())
    }
}
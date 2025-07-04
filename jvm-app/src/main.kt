import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import ui.MainScreen
import ui.di.initKoin

fun main() {
    initKoin()
    application {
        Window(onCloseRequest = ::exitApplication, title = "Overwatch Player Search") {
            MainScreen()
        }
    }
}
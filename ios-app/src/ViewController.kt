import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController
import ui.MainScreen
import ui.di.UIModule

fun ViewController(): UIViewController {

    return ComposeUIViewController { MainScreen() }
}
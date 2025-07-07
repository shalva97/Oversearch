package io.github.shalva97

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.github.kdroidfilter.platformtools.darkmodedetector.mac.setMacOsAdaptiveTitleBar
import io.github.shalva97.di.initOversearchApp

fun main() {
    setMacOsAdaptiveTitleBar()
    initOversearchApp()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Oversearch",
        ) {
            App()
        }
    }
}

package app

import android.app.Application
import ui.di.initKoin

class OversearchApp : Application() {
    override fun onCreate() {
        initKoin()
        super.onCreate()
    }
}
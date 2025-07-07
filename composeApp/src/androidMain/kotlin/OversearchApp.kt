package app

import android.app.Application
import co.touchlab.crashkios.crashlytics.enableCrashlytics
import io.github.shalva97.di.initOversearchApp

class OversearchApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initOversearchApp()
        enableCrashlytics()
    }
}

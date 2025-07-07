package data

import co.touchlab.crashkios.crashlytics.CrashlyticsKotlin

actual class CrashlyticsManager actual constructor() {
    actual fun recordHandledException(e: kotlin.Exception) {
        CrashlyticsKotlin.sendHandledException(e)
    }
}

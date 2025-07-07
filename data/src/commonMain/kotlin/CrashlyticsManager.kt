package data

expect class CrashlyticsManager() {
    fun recordHandledException(e: Exception)
}

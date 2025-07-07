plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.composeHotReload) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    kotlin("plugin.serialization") version "2.1.20"
    id("com.google.firebase.crashlytics") version "3.0.4" apply false
    id("com.google.gms.google-services") version "4.4.3" apply false
    id("com.codingfeline.buildkonfig") version "0.17.1" apply false
}

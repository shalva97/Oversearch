// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    id("com.google.dagger.hilt.android") version "2.55" apply false
    id("org.jlleitschuh.gradle.ktlint") version "12.1.2"
    alias(libs.plugins.kotlinCompose) apply false
}

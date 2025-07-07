import java.io.File

rootProject.name = "Oversearch"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        maven {
            url = uri("https://maven.pkg.github.com/shalva97/overwatch-player-search-api")
            credentials {
                val localProperties = java.util.Properties()
                val localPropertiesFile = File("local.properties")
                if (localPropertiesFile.exists()) {
                    localProperties.load(localPropertiesFile.inputStream())
                    username = localProperties.getProperty("gpr.user")
                    password = localProperties.getProperty("gpr.key")
                }
            }
        }
        mavenCentral()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

include(":composeApp")
include(":data")
import java.io.FileInputStream
import java.util.Properties

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://maven.pkg.github.com/shalva97/overwatch-player-search-api")
            credentials {
                val properties = Properties().apply {
                    runCatching { load(FileInputStream("local.properties")) }
                        .onFailure {
                            println("Failed to load properties. You need to authenticate with Github Packages to download all dependencies")
                        }
                }
                username = properties["gpr.user"] as String? ?: System.getenv("USERNAME")
                password = properties["gpr.key"] as String? ?: System.getenv("TOKEN")
            }
        }
    }
}

rootProject.name = "Oversearch"
include(":app")

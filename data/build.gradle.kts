import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    kotlin("plugin.serialization")
    id("com.codingfeline.buildkonfig")
}

kotlin {

    androidTarget()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    )

    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutinesCore)
            implementation(libs.library)
            implementation(libs.koin.core)
            implementation(libs.kotlinx.serialization.json)

        }
        commonTest.dependencies {

        }
        nativeMain.dependencies {
            api("co.touchlab.crashkios:crashlytics:0.8.6")
        }
        androidMain.dependencies {
            api("co.touchlab.crashkios:crashlytics:0.8.6")
        }
    }
}

android {
    namespace = "io.github.shalva97"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
        }
        getByName("release") {
            isMinifyEnabled = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

buildkonfig {
    packageName = "io.github.shalva97"
    exposeObjectWithName = "BuildKonfig"

    defaultConfigs {
        buildConfigField(STRING, "name", "value")
    }

    // flavor is passed as a first argument of defaultConfigs
    defaultConfigs("dev") {
        buildConfigField(STRING, "name", "devValue")
    }
}

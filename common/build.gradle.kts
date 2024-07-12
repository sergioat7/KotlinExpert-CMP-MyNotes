@file:Suppress("OPT_IN_USAGE")

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    applyDefaultHierarchyTemplate()

    jvm("desktop")
    js(IR) {
        browser()
    }
    androidTarget()
    listOf(
        iosArm64(),
        iosX64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "common"
        }
    }

    sourceSets {

        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.ui)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)
                implementation(compose.materialIconsExtended)
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.serialization.kotlinx.json)
                implementation(libs.voyager.navigator)
                implementation(libs.voyager.screenmodel)
            }
        }

        val commonComposeKmpMain by creating {
            dependsOn(commonMain)
        }

        val desktopMain by getting {
            dependsOn(commonComposeKmpMain)
            dependencies {
                implementation(libs.kotlinx.coroutines.swing)
                implementation(libs.ktor.client.okhttp)
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(compose.web.core)
                implementation(compose.runtime)
            }
        }

        val androidMain by getting {
            dependsOn(commonComposeKmpMain)
            dependencies {
                implementation(libs.kotlinx.coroutines.android)
                implementation(libs.ktor.client.okhttp)
            }
        }

        val iosMain by getting
    }
}

android {
    namespace = "com.aragones.sergio.kotlinexpert"
    compileSdk = 34
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
        targetSdk = 34
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}


plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

kotlin {

    js(IR){
        browser()
        binaries.executable()
    }

    sourceSets {

        val jsMain by getting {
            dependencies {
                implementation(project(":common"))
                implementation(compose.web.core)
                implementation(compose.runtime)
                implementation(libs.ktor.client.js)
            }
        }
    }
}

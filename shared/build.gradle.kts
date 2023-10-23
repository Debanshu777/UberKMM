plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    id("com.google.devtools.ksp") version "1.9.10-1.0.13"
    id("com.rickclephas.kmp.nativecoroutines") version "1.0.0-ALPHA-18"
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                //put your multiplatform dependencies here
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.kotlinx.coroutines.play.services)
                implementation(libs.play.services.location)
                implementation(libs.androidx.startup.runtime)
            }
        }
    }

    kotlin.sourceSets.all {
        languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
    }
}

android {
    namespace = "com.debanshu777.uberkmm"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}
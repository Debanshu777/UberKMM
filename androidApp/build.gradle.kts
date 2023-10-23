import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.debanshu777.uberkmm.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.debanshu777.uberkmm.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        val keyProps = Properties().apply {
            file("../local.properties").takeIf { it.exists() }?.inputStream()?.use { load(it) }
        }
        manifestPlaceholders["GOOGLE_MAP_API"] = keyProps.getProperty("GOOGLE_MAP_API")
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(projects.shared)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.androidx.activity.compose)
    debugImplementation(libs.compose.ui.tooling)
    // Handling Permission scenario
    implementation(libs.permission.flow.compose)
    // Getting Google Maps
    implementation(libs.maps.compose)
    implementation(libs.play.services.maps)
}
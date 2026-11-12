import java.util.Properties

val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localProperties.load(localPropertiesFile.inputStream())
}
var rawKey: String = localProperties.getProperty("OMDB_API_KEY") ?: ""
if (rawKey.contains("apikey=")) {
    rawKey = rawKey.substringAfter("apikey=").substringBefore("&")
}
val omdbApiKey: String = rawKey.trim().ifEmpty { "dbec1d89" }

plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.formacion.buscadordepeliculas"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.formacion.buscadordepeliculas"
        minSdk = 29
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "OMDB_API_KEY", "\"$omdbApiKey\"")
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        release {
            optimization {
                enable = true
                packageScope = setOf("androidx.**", "kotlin.**", "kotlinx.**")
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.androidx.recyclerview)
    implementation(libs.picasso)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}